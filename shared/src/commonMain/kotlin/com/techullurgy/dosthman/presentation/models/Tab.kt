package com.techullurgy.dosthman.presentation.models

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.AnnotatedString

@Stable
internal data class Tab(
    val tabId: String = "",
    val tabName: AnnotatedString = AnnotatedString("Tab"),
    val isSavePending: Boolean = false
)