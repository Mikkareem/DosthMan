package com.techullurgy.dosthman.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.techullurgy.dosthman.presentation.utils.theme.LocalExtendedColors

@Composable
internal fun CreateNewRequestButton(
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = LocalExtendedColors.current.accentBlue
        ),
        onClick = onClick
    ) {
        Text("Create New Request")
    }
}