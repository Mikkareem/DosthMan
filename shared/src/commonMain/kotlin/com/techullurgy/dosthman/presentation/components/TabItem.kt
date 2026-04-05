package com.techullurgy.dosthman.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techullurgy.dosthman.presentation.components.root.AppEditableText
import com.techullurgy.dosthman.presentation.models.UiHttpMethod
import com.techullurgy.dosthman.presentation.utils.theme.ColorToken

@Composable
internal fun TabItem(
    method: UiHttpMethod,
    name: String,
    isSelected: Boolean,
    onTabSelected: () -> Unit,
    onTabNameChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = if(isSelected) RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp) else RectangleShape

    val colors = if(isSelected) {
        ButtonDefaults.buttonColors(
            containerColor = ColorToken.Purple700.color,
            contentColor = ColorToken.Purple700.contentColor
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = ColorToken.NeutralBlack200.contentColor
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .background(
                color = colors.containerColor,
                shape = shape
            )
            .clickable(
                onClick = onTabSelected
            )
            .padding(8.dp)
    ) {
        Text(
            text = method.name.uppercase(),
            color = method.color,
            fontWeight = FontWeight.Bold
        )

        val nameTextStyle = LocalTextStyle.current.copy(color = colors.contentColor)
        AppEditableText(
            text = name,
            onTextChange = onTabNameChanged,
            textStyle = isSelected.takeIf { it }
                ?.let { nameTextStyle.copy(fontWeight = FontWeight.Bold) } ?: nameTextStyle,
        )
    }
}