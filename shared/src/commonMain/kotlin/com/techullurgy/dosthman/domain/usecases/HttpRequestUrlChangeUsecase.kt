package com.techullurgy.dosthman.domain.usecases

import com.techullurgy.dosthman.domain.models.SaveableRequestResponse
import com.techullurgy.dosthman.domain.storage.InMemoryStorage
import org.koin.core.annotation.Single

@Single
internal class HttpRequestUrlChangeUsecase(
    private val retrieveToInMemory: RetrieveFromPersistentToInMemoryStorageUsecase,
    private val inMemoryStorage: InMemoryStorage<SaveableRequestResponse>
) {
    suspend operator fun invoke(
        requestKey: String,
        url: String,
    ) {
        val currentInput = retrieveToInMemory(requestKey)

        val newInput = currentInput.copy(
            request = currentInput.request.copy(
                url = url
            )
        )

        inMemoryStorage.save(requestKey, newInput)
    }
}