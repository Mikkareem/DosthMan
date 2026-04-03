package com.techullurgy.dosthman.domain.usecases

import com.techullurgy.dosthman.domain.models.SaveableRequestResponse
import com.techullurgy.dosthman.domain.storage.InMemoryStorage
import org.koin.core.annotation.Single

@Single
internal class HttpRequestHeadersChangeUsecase(
    private val retrieveToInMemory: RetrieveFromPersistentToInMemoryStorageUsecase,
    private val inMemoryStorage: InMemoryStorage<SaveableRequestResponse>
) {
    suspend operator fun invoke(
        requestKey: String,
        headers: Map<String, String>,
    ) {
        val currentInput = retrieveToInMemory(requestKey)

        val newInput = currentInput.copy(
            request = currentInput.request.copy(
                headers = headers
            )
        )

        inMemoryStorage.save(requestKey, newInput)
    }
}