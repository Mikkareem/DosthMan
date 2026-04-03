package com.techullurgy.dosthman.presentation.models

import androidx.compose.runtime.Stable

@Stable
internal data class UiHttpStatusCode(
    val code: Int,
    val text: String
)