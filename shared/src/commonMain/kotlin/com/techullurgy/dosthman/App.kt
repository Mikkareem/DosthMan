package com.techullurgy.dosthman

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.techullurgy.dosthman.presentation.screens.main.MainScreen
import com.techullurgy.dosthman.presentation.utils.theme.DosthManTheme

@Composable
@Preview
fun App() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DosthManTheme {
            MainScreen()
        }
    }
}