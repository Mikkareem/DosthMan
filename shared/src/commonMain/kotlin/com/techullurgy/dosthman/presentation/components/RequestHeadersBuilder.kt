package com.techullurgy.dosthman.presentation.components

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun RequestHeadersBuilder(
    headersState: List<Pair<TextFieldState, TextFieldState>>,
    modifier: Modifier = Modifier
) {
    RequestKeyValueBuilder(
        states = headersState,
        modifier = modifier
    )
}