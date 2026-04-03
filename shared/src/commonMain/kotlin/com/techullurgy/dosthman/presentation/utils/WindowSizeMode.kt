package com.techullurgy.dosthman.presentation.utils

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.window.core.layout.WindowWidthSizeClass

enum class WindowSizeMode {
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    DESKTOP
}

val LocalWindowSizeMode = staticCompositionLocalOf<WindowSizeMode> {
    error("No value passed for LocalWindowSizeMode")
}

@Composable
internal fun windowSizeMode(): WindowSizeMode {
    val windowInfo = currentWindowAdaptiveInfo()

    val widthClass = windowInfo.windowSizeClass.windowWidthSizeClass

    return when {
        widthClass == WindowWidthSizeClass.COMPACT -> WindowSizeMode.MOBILE_PORTRAIT
        widthClass == WindowWidthSizeClass.MEDIUM -> WindowSizeMode.MOBILE_LANDSCAPE
        else -> WindowSizeMode.DESKTOP
    }
}