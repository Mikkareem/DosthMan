package com.techullurgy.dosthman.domain.usecases

import com.techullurgy.dosthman.domain.models.PersistentRequestResponse
import com.techullurgy.dosthman.domain.models.SaveableRequestResponse
import com.techullurgy.dosthman.domain.storage.InMemoryStorage
import com.techullurgy.dosthman.domain.storage.PersistentStorage
import org.koin.core.annotation.Single

@Single
internal class SaveRequestUsecase(
    private val inMemoryStorage: InMemoryStorage<SaveableRequestResponse>,
    private val persistentStorage: PersistentStorage<PersistentRequestResponse>
) {
    suspend operator fun invoke(requestId: String) {
        val saveableRequestResponse = inMemoryStorage.findByKey(requestId) ?: throw RuntimeException()

        if(saveableRequestResponse.isLoading) {
            throw RuntimeException()
        }

        val persistentRequestResponse = PersistentRequestResponse(
            id = requestId,
            request = saveableRequestResponse.request,
            response = saveableRequestResponse.response
        )

        persistentStorage.save(requestId, persistentRequestResponse)

        inMemoryStorage.remove(requestId)
    }
}