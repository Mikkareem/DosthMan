package com.techullurgy.dosthman.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.techullurgy.dosthman.presentation.models.ResponseViewerTab

@Composable
internal fun ResponseViewerTabs(
    selectedTab: ResponseViewerTab,
    onViewerTabSelected: (ResponseViewerTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = ResponseViewerTab.entries

    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
    ) {
        tabs.forEach { tab ->
            TextButton(
                onClick = { onViewerTabSelected(tab) }
            ) {
                when (tab) {
                    ResponseViewerTab.Body -> {
                        Text(
                            text = "Body",
                            fontWeight = if(selectedTab == tab) FontWeight.Bold else null
                        )
                    }
                    ResponseViewerTab.Cookies -> {
                        Text(
                            text = "Cookies",
                            fontWeight = if(selectedTab == tab) FontWeight.Bold else null
                        )
                    }
                    ResponseViewerTab.Headers -> {
                        Text(
                            text = "Headers",
                            fontWeight = if(selectedTab == tab) FontWeight.Bold else null
                        )
                    }
                }
            }
        }
    }
}