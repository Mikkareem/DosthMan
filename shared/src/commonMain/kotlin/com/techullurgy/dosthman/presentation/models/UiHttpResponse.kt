package com.techullurgy.dosthman.presentation.models

import androidx.compose.runtime.Stable

@Stable
internal sealed interface UiHttpResponse {

    @Stable
    data class Failure(val message: String): UiHttpResponse

    @Stable
    data class Data(
        val requestKey: String,
        val status: UiHttpStatus,
        val statusCode: UiHttpStatusCode,
        val requestHeaders: Map<String, List<String>>,
        val responseHeaders: Map<String, List<String>>,
        val timeTaken: Long,
        val content: String,
        val contentType: UiContentType
    ): UiHttpResponse
}