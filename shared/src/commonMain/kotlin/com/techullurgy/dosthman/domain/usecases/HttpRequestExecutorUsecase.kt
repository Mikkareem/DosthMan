package com.techullurgy.dosthman.domain.usecases

import com.techullurgy.dosthman.domain.callers.NetworkCaller
import com.techullurgy.dosthman.domain.models.PersistentRequestResponse
import com.techullurgy.dosthman.domain.models.SaveableRequestResponse
import com.techullurgy.dosthman.domain.storage.InMemoryStorage
import com.techullurgy.dosthman.domain.storage.PersistentStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
internal class HttpRequestExecutorUsecase(
    private val networkCaller: NetworkCaller,
    private val appScope: CoroutineScope,
    private val inMemoryStorage: InMemoryStorage<SaveableRequestResponse>,
    private val persistentStorage: PersistentStorage<PersistentRequestResponse>
) {
    operator fun invoke(requestKey: String) {
        appScope.launch {
            val request = inMemoryStorage.findByKey(requestKey)?.let {
                val loadingState = it.copy(isLoading = true)
                inMemoryStorage.save(requestKey,loadingState)
                it.request
            } ?: run {
                persistentStorage.findByKey(requestKey)!!.let {
                    val loadingState = it.copy(isLoading = true)
                    persistentStorage.save(requestKey,loadingState)
                    it.request
                }
            }

            // TODO: Parsing here (for variables in Request)
            println("Request: $request")

            val response = withContext(Dispatchers.IO) { networkCaller.sendRequest(request) }

            inMemoryStorage.findByKey(requestKey)?.also {
                val responseState = it.copy(isLoading = false, response = response)
                inMemoryStorage.save(requestKey,responseState)
            } ?: run {
                persistentStorage.findByKey(requestKey)!!.let {
                    val responseState = it.copy(isLoading = false, response = response)
                    persistentStorage.save(requestKey,responseState)
                }
            }
        }
    }
}