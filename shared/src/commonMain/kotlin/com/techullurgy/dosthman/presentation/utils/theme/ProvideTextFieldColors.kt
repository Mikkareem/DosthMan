package com.techullurgy.dosthman.presentation.utils.theme

import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalTextFieldColors = staticCompositionLocalOf<TextFieldColors> { error("No value passed for LocalTextFieldColors") }

@Composable
internal fun ProvideTextFieldColors(
    colors: TextFieldColors = TextFieldDefaults.colors(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTextFieldColors provides colors
    ) {
        content.invoke()
    }
}