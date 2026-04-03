package com.techullurgy.dosthman.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ResponseHeaderViewer(
    headers: Map<String, String>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        headers.forEach { (key, value) ->
            item {
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = LocalContentColor.current.copy(alpha = 0.6f)
                        )
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = key,
                        maxLines = 1,
                        modifier = Modifier.horizontalScroll(rememberScrollState())
                    )
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = LocalContentColor.current.copy(alpha = 0.6f)
                        )
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = value,
                        maxLines = 1,
                        modifier = Modifier.horizontalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}