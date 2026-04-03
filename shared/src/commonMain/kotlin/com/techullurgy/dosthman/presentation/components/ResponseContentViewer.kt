package com.techullurgy.dosthman.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.techullurgy.dosthman.presentation.models.UiContentType

@Composable
internal fun ResponseContentViewer(
    content: String,
    contentType: UiContentType,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
            .verticalScroll(rememberScrollState())
    ) {
        when(contentType) {
            UiContentType.ApplicationJson -> {
                JsonViewer(
                    json = content,
                    modifier = Modifier.matchParentSize()
                )
            }
            UiContentType.FormUrlEncoded -> {}
            UiContentType.TextPlain -> {
                Text(content)
            }
        }
    }
}