package com.techullurgy.dosthman.presentation.utils.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalExtendedColors = compositionLocalOf<ExtendedColors> { error("No value passed for LocalExtendedColors") }

@Stable
data class ExtendedColors(
    val accentPink: Color,
    val accentGreen: Color,
    val accentBlue: Color,
    val accentYellow: Color,
    val accentOrange: Color,

    val container1Light: Color,
    val container1Dark: Color,
    val container2Light: Color,
    val container2Dark: Color,
    val container3Light: Color,
    val container3Dark: Color,
)

val MaterialTheme.extendedColors: ExtendedColors
    @Composable @ReadOnlyComposable get() = LocalExtendedColors.current