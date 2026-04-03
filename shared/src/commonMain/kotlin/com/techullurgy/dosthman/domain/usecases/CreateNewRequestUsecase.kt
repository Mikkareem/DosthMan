package com.techullurgy.dosthman.domain.usecases

import com.techullurgy.dosthman.domain.models.AppHttpMethod
import com.techullurgy.dosthman.domain.models.AppHttpRequest
import com.techullurgy.dosthman.domain.models.SaveableRequestResponse
import com.techullurgy.dosthman.domain.storage.InMemoryStorage
import org.koin.core.annotation.Single

@Single
class CreateNewRequestUsecase(
    private val inMemoryStorage: InMemoryStorage<SaveableRequestResponse>
) {
    operator fun invoke(requestId: String) {
        val newRequest = SaveableRequestResponse(
            request = AppHttpRequest(
                requestKey = requestId,
                method = AppHttpMethod.Get(),
                url = "https://jsonplaceholder.typicode.com/posts"
            ),
            isLoading = false
        )

        inMemoryStorage.save(requestId, newRequest)
    }
}