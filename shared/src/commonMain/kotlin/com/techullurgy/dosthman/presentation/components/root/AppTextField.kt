package com.techullurgy.dosthman.presentation.components.root

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techullurgy.dosthman.presentation.utils.theme.ColorToken
import com.techullurgy.dosthman.presentation.utils.theme.LocalTextFieldColors

@Composable
fun AppTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    colors: TextFieldColors = LocalTextFieldColors.current,
    textStyle: TextStyle = LocalTextStyle.current,
    shape: Shape = RectangleShape,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val focused by interactionSource.collectIsFocusedAsState()

    val modifiedTextStyle = textStyle.merge(
        TextStyle(
            color = if(focused) colors.focusedTextColor else colors.unfocusedTextColor
        )
    )

    BasicTextField(
        state = state,
        modifier = modifier,
        lineLimits = if(singleLine) TextFieldLineLimits.SingleLine else TextFieldLineLimits.Default,
        interactionSource = interactionSource,
        textStyle = modifiedTextStyle,
        readOnly = readOnly,
        decorator = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = if(focused) colors.focusedContainerColor else colors.unfocusedContainerColor,
                        shape = shape
                    )
                    .border(
                        width = 2.dp,
                        color = if(focused) colors.focusedIndicatorColor else colors.unfocusedIndicatorColor,
                        shape = shape
                    )
                    .padding(10.dp)
            ) {
                innerTextField()
            }
        }
    )
}

@Composable
@Preview
private fun AppTextFieldPreview() {
    Box(
        Modifier
            .background(ColorToken.NeutralBlack200.color)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AppTextField(
            state = TextFieldState("GET"),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ColorToken.NeutralTransparent.color,
                unfocusedContainerColor = ColorToken.NeutralTransparent.color,
                focusedIndicatorColor = ColorToken.Green300.color,
                unfocusedIndicatorColor = ColorToken.Green400.color,
                unfocusedTextColor = ColorToken.NeutralBlack200.contentColor,
                focusedTextColor = ColorToken.NeutralBlack200.contentColor,
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.width(100.dp)
        )
    }
}