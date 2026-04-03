package com.techullurgy.dosthman.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun RequestSendButton(
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onSendClick,
        modifier = modifier
    ) {
        Text(
            text = "Send",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}