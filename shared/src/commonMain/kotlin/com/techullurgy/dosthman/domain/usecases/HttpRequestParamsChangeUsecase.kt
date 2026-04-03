package com.techullurgy.dosthman.domain.usecases

import com.techullurgy.dosthman.domain.models.SaveableRequestResponse
import com.techullurgy.dosthman.domain.storage.InMemoryStorage
import org.koin.core.annotation.Single

@Single
internal class HttpRequestParamsChangeUsecase(
    private val retrieveToInMemory: RetrieveFromPersistentToInMemoryStorageUsecase,
    private val inMemoryStorage: InMemoryStorage<SaveableRequestResponse>
) {
    suspend operator fun invoke(
        requestKey: String,
        params: Map<String, String>,
    ) {
        val currentInput = retrieveToInMemory(requestKey)

        val newInput = currentInput.copy(
            request = currentInput.request.copy(
                params = params
            )
        )

        inMemoryStorage.save(requestKey, newInput)
    }
}