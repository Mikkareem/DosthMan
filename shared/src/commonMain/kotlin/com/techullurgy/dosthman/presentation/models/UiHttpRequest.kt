package com.techullurgy.dosthman.presentation.models

import androidx.compose.runtime.Stable

@Stable
internal data class UiHttpRequest(
    val requestKey: String,
    val method: UiHttpMethod,
    val url: String,
    val name: String?,
    val params: List<Pair<String, String>> = emptyList(),
    val headers: List<Pair<String, String>> = emptyList(),
)