package com.techullurgy.dosthman.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techullurgy.dosthman.presentation.models.RequestBuilderTab

@Composable
internal fun RequestBuilderTabs(
    builderTabs: List<RequestBuilderTab>,
    selectedBuilderTab: RequestBuilderTab,
    onBuilderTabSelected: (RequestBuilderTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
    ) {
        builderTabs.forEach { tab ->
            TextButton(
                onClick = { onBuilderTabSelected(tab) }
            ) {
                when(tab) {
                    is RequestBuilderTab.Params -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Params",
                                fontWeight = if(selectedBuilderTab is RequestBuilderTab.Params) FontWeight.Bold else null
                            )
                            Text(tab.count.toString())
                        }
                    }
                    is RequestBuilderTab.Authorization -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Authorization",
                                fontWeight = if(selectedBuilderTab is RequestBuilderTab.Authorization) FontWeight.Bold else null
                            )
                            AnimatedVisibility(
                                visible = tab.isProvided
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .drawBehind {
                                            drawCircle(color = Color.Green)
                                        }
                                )
                            }
                        }
                    }
                    is RequestBuilderTab.Headers -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Headers",
                                fontWeight = if(selectedBuilderTab is RequestBuilderTab.Headers) FontWeight.Bold else null
                            )
                            Text(tab.count.toString())
                        }
                    }
                    is RequestBuilderTab.Body -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Body",
                                fontWeight = if(selectedBuilderTab is RequestBuilderTab.Body) FontWeight.Bold else null
                            )
                            AnimatedVisibility(
                                visible = tab.isProvided
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .drawBehind {
                                            drawCircle(color = Color.Green)
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}