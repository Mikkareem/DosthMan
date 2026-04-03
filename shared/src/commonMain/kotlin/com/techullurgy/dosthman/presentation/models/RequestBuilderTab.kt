package com.techullurgy.dosthman.presentation.models

import androidx.compose.runtime.Stable

@Stable
internal sealed interface RequestBuilderTab {
    @Stable data class Params(val count: Int = 0): RequestBuilderTab
    @Stable data class Authorization(val isProvided: Boolean = false): RequestBuilderTab
    @Stable data class Headers(val count: Int = 0): RequestBuilderTab
    @Stable data class Body(val isProvided: Boolean = false): RequestBuilderTab
}