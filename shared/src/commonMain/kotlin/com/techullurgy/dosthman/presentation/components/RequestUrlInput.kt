package com.techullurgy.dosthman.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techullurgy.dosthman.presentation.components.root.AppTextField
import com.techullurgy.dosthman.presentation.utils.theme.LocalTextFieldColors

@Composable
internal fun RequestUrlInput(
    urlState: TextFieldState,
    modifier: Modifier = Modifier
) {
    val textFieldColors = LocalTextFieldColors.current

    AppTextField(
        state = urlState,
        textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
        colors = textFieldColors,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    )
}