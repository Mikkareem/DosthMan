package com.techullurgy.dosthman.presentation.models

import androidx.compose.runtime.Stable

@Stable
internal data class Tab(
    val tabId: String = "",
    val tabName: String = "Tab",
    val method: UiHttpMethod = UiHttpMethod.Get,
    val isSavePending: Boolean = false
)