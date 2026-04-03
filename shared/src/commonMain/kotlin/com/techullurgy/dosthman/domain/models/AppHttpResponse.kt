package com.techullurgy.dosthman.domain.models

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppHttpResponse {

    @Serializable
    data class Failure(val message: String): AppHttpResponse

    @Serializable
    data class Data(
        val requestKey: String,
        val statusCode: AppHttpStatusCode,
        val requestHeaders: Map<String, List<String>>,
        val responseHeaders: Map<String, List<String>>,
        val timeTaken: Long,
        val content: String,
        val contentType: AppContentType
    ): AppHttpResponse {

        val status = statusCode.getAppHttpStatus()
    }
}