package com.techullurgy.dosthman.presentation.components

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun RequestParamsBuilder(
    paramsState: List<Pair<TextFieldState, TextFieldState>>,
    modifier: Modifier = Modifier
) {
    RequestKeyValueBuilder(
        states = paramsState,
        modifier = modifier
    )
}