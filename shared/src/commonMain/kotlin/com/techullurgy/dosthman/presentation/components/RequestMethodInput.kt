package com.techullurgy.dosthman.presentation.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techullurgy.dosthman.presentation.components.root.AppTextField
import com.techullurgy.dosthman.presentation.models.UiHttpMethod
import com.techullurgy.dosthman.presentation.utils.theme.LocalTextFieldColors
import com.techullurgy.dosthman.presentation.utils.theme.extendedColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RequestMethodInput(
    method: UiHttpMethod,
    onMethodChange: (UiHttpMethod) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    var value by remember(method) { mutableStateOf(method) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = modifier
            .width(100.dp)
    ) {
        val textFieldColors = LocalTextFieldColors.current

        AppTextField(
            state = TextFieldState(value.name.uppercase()),
            readOnly = true,
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
            colors = textFieldColors.copy(
                focusedTextColor = value.color,
                unfocusedTextColor = value.color
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .menuAnchor(
                    ExposedDropdownMenuAnchorType.PrimaryNotEditable
                )
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            containerColor = MaterialTheme.extendedColors.container1Dark,
            onDismissRequest = { isExpanded = false },
        ) {
            UiHttpMethod.entries.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it.name.uppercase(), color = it.color)
                    },
                    onClick = {
                        value = it
                        isExpanded = false
                        onMethodChange(it)
                    },
                    contentPadding = MenuDefaults.DropdownMenuItemContentPadding
                )
            }
        }
    }
}