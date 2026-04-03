package com.techullurgy.dosthman.domain.usecases

import com.techullurgy.dosthman.domain.models.PersistentRequestResponse
import com.techullurgy.dosthman.domain.models.SaveableRequestResponse
import com.techullurgy.dosthman.domain.storage.InMemoryStorage
import com.techullurgy.dosthman.domain.storage.PersistentStorage
import org.koin.core.annotation.Single

@Single
internal class RetrieveFromPersistentToInMemoryStorageUsecase(
    private val inMemoryStorage: InMemoryStorage<SaveableRequestResponse>,
    private val persistentStorage: PersistentStorage<PersistentRequestResponse>
) {
    suspend operator fun invoke(
        requestKey: String
    ): SaveableRequestResponse {
        val result = inMemoryStorage.findByKey(requestKey)
        if(result != null) {
            return result
        }

        val persistentResult = persistentStorage.findByKey(requestKey) ?: throw RuntimeException()

        return SaveableRequestResponse(
            request = persistentResult.request,
            response = persistentResult.response,
            isLoading = persistentResult.isLoading
        )
    }
}