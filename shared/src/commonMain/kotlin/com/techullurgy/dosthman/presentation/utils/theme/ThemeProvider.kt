package com.techullurgy.dosthman.presentation.utils.theme

import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.techullurgy.dosthman.presentation.utils.LocalWindowSizeMode
import com.techullurgy.dosthman.presentation.utils.windowSizeMode

@Composable
internal fun ThemeProvider(
    content: @Composable () -> Unit
) {

    val windowSizeMode = windowSizeMode()

    CompositionLocalProvider(
        LocalWindowSizeMode provides windowSizeMode,
        LocalContentColor provides ColorToken.NeutralWhite100.color,
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = ColorToken.Blue400.color,
            backgroundColor = ColorToken.Yellow700.color
        ),
        LocalExtendedColors provides ExtendedColors(
            accentBlue = ColorToken.Blue500.color,
            accentPink = ColorToken.Pink500.color,
            accentGreen = ColorToken.Green500.color,
            accentOrange = ColorToken.Orange500.color,
            accentYellow = ColorToken.Yellow500.color,
            container1Light = ColorToken.NeutralBlack700.color,
            container1Dark = ColorToken.NeutralBlack300.color,
            container2Light = ColorToken.NeutralBlack800.color,
            container2Dark = ColorToken.NeutralBlack200.color,
            container3Light = ColorToken.NeutralBlack900.color,
            container3Dark = ColorToken.NeutralBlack100.color,
        )
    ) {
        ProvideTextFieldColors(
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = ColorToken.NeutralTransparent.color,
                focusedContainerColor = ColorToken.NeutralTransparent.color,
                cursorColor = ColorToken.Yellow500.color,
                focusedTextColor = LocalContentColor.current,
                unfocusedTextColor = LocalContentColor.current,
                focusedBorderColor = ColorToken.NeutralWhite700.color,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            )
        ) {
            content.invoke()
        }
    }
}