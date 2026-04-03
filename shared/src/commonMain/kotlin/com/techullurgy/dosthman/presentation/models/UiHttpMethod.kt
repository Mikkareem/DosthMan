package com.techullurgy.dosthman.presentation.models

import androidx.compose.ui.graphics.Color
import com.techullurgy.dosthman.presentation.utils.theme.ColorToken

internal enum class UiHttpMethod(val color: Color) {
    Get(ColorToken.Green300.color),
    Post(ColorToken.Yellow300.color),
    Put(ColorToken.Teal200.color),
    Patch(ColorToken.Teal500.color),
    Delete(ColorToken.Red500.color),
    Head(ColorToken.Purple500.color),
    Options(ColorToken.Blue300.color)
}