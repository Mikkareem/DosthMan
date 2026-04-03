package com.techullurgy.dosthman.presentation.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.techullurgy.dosthman.presentation.components.root.AppTextField
import com.techullurgy.dosthman.presentation.utils.theme.LocalTextFieldColors

@Composable
internal fun RequestKeyValueBuilder(
    states: List<Pair<TextFieldState, TextFieldState>>,
    modifier: Modifier = Modifier
) {
    val textFieldColors = LocalTextFieldColors.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
    ) {
        item {
            Text(
                text = "Key",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        item {
            Text(
                text = "Value",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        states.forEach { state ->
            item {
                AppTextField(
                    state = state.first,
                    colors = textFieldColors,
                    shape = RectangleShape
                )
            }

            item {
                AppTextField(
                    state = state.second,
                    colors = textFieldColors,
                    shape = RectangleShape
                )
            }
        }
    }
}