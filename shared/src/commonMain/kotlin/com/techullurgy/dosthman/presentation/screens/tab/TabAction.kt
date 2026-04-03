package com.techullurgy.dosthman.presentation.screens.tab

import com.techullurgy.dosthman.presentation.models.UiHttpMethod

internal sealed interface TabAction {
    data object OnSaveTriggered: TabAction
    data object OnSendRequestTriggered: TabAction
    data class OnHttpMethodChange(val method: UiHttpMethod): TabAction
    data class OnHttpUrlChange(val url: String): TabAction
    data class OnParamsChange(val params: List<Pair<String, String>>): TabAction
    data class OnHeadersChange(val headers: List<Pair<String, String>>): TabAction
}