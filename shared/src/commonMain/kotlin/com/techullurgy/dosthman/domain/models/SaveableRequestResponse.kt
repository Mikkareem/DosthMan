package com.techullurgy.dosthman.domain.models

data class SaveableRequestResponse(
    val request: AppHttpRequest,
    val response: AppHttpResponse? = null,
    val isLoading: Boolean = false
)