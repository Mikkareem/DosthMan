package com.techullurgy.dosthman.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.techullurgy.dosthman.presentation.utils.theme.ColorToken

@Composable
internal fun TabItem(
    name: AnnotatedString,
    isSelected: Boolean,
    onTabSelected: () -> Unit,
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

    Box(
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
            text = name,
            fontWeight = isSelected.takeIf { it }?.let { FontWeight.Bold },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = colors.contentColor
        )
    }
}