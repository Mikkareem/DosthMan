package com.techullurgy.dosthman

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.techullurgy.dosthman.di.initApp
import com.techullurgy.dosthman.presentation.utils.WindowSizeMode

fun main() {
    runApplication()
}

private fun runApplication() {
    initApp()

    val width = Unit.run {
        val mode = WindowSizeMode.MOBILE_PORTRAIT

        when(mode) {
            WindowSizeMode.MOBILE_PORTRAIT -> 380.dp
            else -> 600.dp
        }
    }

    application {
        val windowState = rememberWindowState(
            width = width,
            height = 700.dp
        )

        Window(
            onCloseRequest = ::exitApplication,
            title = "DosthMan",
            state = windowState
        ) {
            App()
        }
    }
}