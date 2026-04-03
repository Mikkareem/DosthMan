package com.techullurgy.dosthman.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class AppHttpRequest(
    val requestKey: String,
    val method: AppHttpMethod,
    val url: String,
    val name: String? = null,
    val params: Map<String, String> = emptyMap(),
    val headers: Map<String, String> = emptyMap(),
)