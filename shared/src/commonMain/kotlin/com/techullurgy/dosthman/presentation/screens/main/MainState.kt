package com.techullurgy.dosthman.presentation.screens.main

import androidx.compose.runtime.Stable
import com.techullurgy.dosthman.presentation.models.Tab

@Stable
internal data class MainState(
    val tabs: List<Tab> = emptyList(),
    val selectedTab: String? = null,
    val openedTabs: Set<String> = emptySet()
) {
    init {
        if(selectedTab != null) {
            require(openedTabs.contains(selectedTab))
        }
    }
}