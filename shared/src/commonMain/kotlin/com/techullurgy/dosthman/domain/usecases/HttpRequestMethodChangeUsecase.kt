package com.techullurgy.dosthman.domain.usecases

import com.techullurgy.dosthman.domain.models.AppHttpMethod
import com.techullurgy.dosthman.domain.models.SaveableRequestResponse
import com.techullurgy.dosthman.domain.storage.InMemoryStorage
import org.koin.core.annotation.Single

@Single
internal class HttpRequestMethodChangeUsecase(
    private val retrieveToInMemory: RetrieveFromPersistentToInMemoryStorageUsecase,
    private val inMemoryStorage: InMemoryStorage<SaveableRequestResponse>
) {
    suspend operator fun invoke(
        requestKey: String,
        method: AppHttpMethod,
    ) {
        val currentInput = retrieveToInMemory(requestKey)

        val newInput = currentInput.copy(
            request = currentInput.request.copy(
                method = method.apply {
                    body = currentInput.request.method.body
                    contentType = currentInput.request.method.contentType
                }
            )
        )

        inMemoryStorage.save(requestKey, newInput)
    }
}