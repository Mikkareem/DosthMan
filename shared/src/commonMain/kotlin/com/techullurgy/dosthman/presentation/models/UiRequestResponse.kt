package com.techullurgy.dosthman.presentation.models

import androidx.compose.runtime.Stable
import com.techullurgy.dosthman.domain.models.AppContentType
import com.techullurgy.dosthman.domain.models.AppHttpMethod
import com.techullurgy.dosthman.domain.models.AppHttpResponse
import com.techullurgy.dosthman.domain.models.AppHttpStatus
import com.techullurgy.dosthman.domain.usecases.RequestResponse

@Stable
internal data class UiRequestResponse(
    val requestKey: String,
    val request: UiHttpRequest,
    val response: UiHttpResponse?,
    val isSavePending: Boolean,
    val isLoading: Boolean
)

internal fun RequestResponse.toUiRequestResponse(): UiRequestResponse {
    return UiRequestResponse(
        requestKey = requestKey,
        request = UiHttpRequest(
            requestKey = request.requestKey,
            method = when (request.method) {
                is AppHttpMethod.Delete -> UiHttpMethod.Delete
                is AppHttpMethod.Get -> UiHttpMethod.Get
                is AppHttpMethod.Head -> UiHttpMethod.Head
                is AppHttpMethod.Options -> UiHttpMethod.Options
                is AppHttpMethod.Patch -> UiHttpMethod.Patch
                is AppHttpMethod.Post -> UiHttpMethod.Post
                is AppHttpMethod.Put -> UiHttpMethod.Put
            },
            url = request.url,
            name = request.name,
            params = request.params.map { it.key to it.value },
            headers = request.headers.map { it.key to it.value }
        ),
        response = response?.let {
            when(it) {
                is AppHttpResponse.Data -> UiHttpResponse.Data(
                    requestKey = it.requestKey,
                    status = when(it.status) {
                        AppHttpStatus.SUCCESS -> UiHttpStatus.SUCCESS
                        AppHttpStatus.FAILURE -> UiHttpStatus.FAILURE
                        AppHttpStatus.BASIC -> UiHttpStatus.BASIC
                        AppHttpStatus.INTERMEDIATE -> UiHttpStatus.INTERMEDIATE
                        AppHttpStatus.UNKNOWN -> UiHttpStatus.UNKNOWN
                    },
                    statusCode = UiHttpStatusCode(
                        code = it.statusCode.code,
                        text = it.statusCode.text
                    ),
                    requestHeaders = it.requestHeaders,
                    responseHeaders = it.responseHeaders,
                    timeTaken = it.timeTaken,
                    content = it.content,
                    contentType = when(it.contentType) {
                        AppContentType.ApplicationJson -> UiContentType.ApplicationJson
                        AppContentType.FormUrlEncoded -> UiContentType.FormUrlEncoded
                        AppContentType.TextPlain -> UiContentType.TextPlain
                    }
                )
                is AppHttpResponse.Failure -> UiHttpResponse.Failure(it.message)
            }
        },
        isSavePending = isSavePending,
        isLoading = isLoading
    )
}