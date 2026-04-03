package com.techullurgy.dosthman.presentation.screens.tab

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import com.techullurgy.dosthman.presentation.models.UiRequestResponse

internal val LocalInspectionTabState = compositionLocalOf<TabState> { error("No value passed for LocalInspectionTabState") }

@Stable
internal data class TabState(
    val requestResponse: UiRequestResponse? = null,
    val urlState: TextFieldState = TextFieldState(),
    val paramsState: List<Pair<TextFieldState, TextFieldState>> = emptyList(),
    val headersState: List<Pair<TextFieldState, TextFieldState>> = emptyList(),
    val bodyContentState: TextFieldState = TextFieldState(),
    val bodyTypeState: TextFieldState = TextFieldState(),
)