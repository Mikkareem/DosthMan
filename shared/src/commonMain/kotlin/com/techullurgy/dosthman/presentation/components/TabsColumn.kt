package com.techullurgy.dosthman.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.techullurgy.dosthman.presentation.models.Tab

@Composable
internal fun TabsColumn(
    tabs: List<Tab>,
    selectedTab: String?,
    onCreateNewRequestClick: () -> Unit,
    onTabSelected: (String) -> Unit,
    onTabNameChanged: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        CreateNewRequestButton(
            onClick = onCreateNewRequestClick
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            tabs.forEach {
                TabItem(
                    method = it.method,
                    name = it.tabName,
                    isSelected = it.tabId == selectedTab,
                    onTabSelected = { onTabSelected(it.tabId) },
                    onTabNameChanged = { c -> onTabNameChanged(it.tabId, c) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}