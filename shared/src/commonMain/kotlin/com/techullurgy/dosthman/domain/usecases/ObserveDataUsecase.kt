package com.techullurgy.dosthman.domain.usecases

import com.techullurgy.dosthman.domain.models.AppHttpRequest
import com.techullurgy.dosthman.domain.models.AppHttpResponse
import com.techullurgy.dosthman.domain.models.PersistentRequestResponse
import com.techullurgy.dosthman.domain.models.SaveableRequestResponse
import com.techullurgy.dosthman.domain.storage.InMemoryStorage
import com.techullurgy.dosthman.domain.storage.PersistentStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import org.koin.core.annotation.Single
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.forEach

@Single
internal class ObserveDataUsecase(
    inMemoryStorage: InMemoryStorage<SaveableRequestResponse>,
    persistentStorage: PersistentStorage<PersistentRequestResponse>,
    appScope: CoroutineScope
) {

    private val dataFlow = combine(
        persistentStorage.data,
        inMemoryStorage.data
    ) { m1, m2 ->
        val returnableMap = mutableMapOf<String, RequestResponse>()

        m1.forEach { (key, value) ->
            returnableMap[key] = RequestResponse(
                requestKey = key,
                request = value.request,
                response = value.response,
                isSavePending = false,
                isLoading = value.isLoading
            )
        }

        m2.forEach { (key, value) ->
            returnableMap[key] = RequestResponse(
                requestKey = key,
                request = value.request,
                response = value.response,
                isSavePending = true,
                isLoading = value.isLoading
            )
        }

        returnableMap.toMap()
    }
        .shareIn(
            started = SharingStarted.WhileSubscribed(5000),
            scope = appScope,
            replay = 1
        )

    operator fun invoke(): Flow<Map<String, RequestResponse>> = dataFlow
}

data class RequestResponse(
    val requestKey: String,
    val request: AppHttpRequest,
    val response: AppHttpResponse?,
    val isSavePending: Boolean,
    val isLoading: Boolean
)