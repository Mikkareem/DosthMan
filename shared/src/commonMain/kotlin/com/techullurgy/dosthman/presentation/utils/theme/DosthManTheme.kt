package com.techullurgy.dosthman.presentation.utils.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun DosthManTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = ColorToken.Green700.color,
            background = ColorToken.NeutralBlack100.color
        )
    ) {
        ThemeProvider {
            content.invoke()
        }
    }
}