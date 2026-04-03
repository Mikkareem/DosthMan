package com.techullurgy.dosthman.presentation.screens.main

internal sealed interface MainAction {
    data object OnCreateNewRequestTriggered: MainAction
    data class OnTabSelected(val tabId: String): MainAction
}