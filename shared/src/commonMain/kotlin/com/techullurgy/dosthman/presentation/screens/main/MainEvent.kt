package com.techullurgy.dosthman.presentation.screens.main

sealed interface MainEvent {
    data object TabChangeEvent: MainEvent
}