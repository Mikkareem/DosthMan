package com.techullurgy.dosthman.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class PersistentRequestResponse(
    val id: String,
    val request: AppHttpRequest,
    val response: AppHttpResponse?,
    val isLoading: Boolean = false
)