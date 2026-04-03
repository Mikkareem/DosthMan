package com.techullurgy.dosthman.presentation.screens.sections

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techullurgy.dosthman.presentation.components.ResponseContentViewer
import com.techullurgy.dosthman.presentation.components.ResponseHeaderViewer
import com.techullurgy.dosthman.presentation.components.ResponseViewerTabs
import com.techullurgy.dosthman.presentation.models.ResponseViewerTab
import com.techullurgy.dosthman.presentation.models.UiHttpResponse
import com.techullurgy.dosthman.presentation.models.UiHttpStatus
import com.techullurgy.dosthman.presentation.models.UiHttpStatusCode
import com.techullurgy.dosthman.presentation.utils.theme.ColorToken

@Composable
internal fun ResponseSection(
    response: UiHttpResponse,
    modifier: Modifier = Modifier
) {
    when(response) {
        is UiHttpResponse.Data -> {
            ResponseSectionData(data = response, modifier = modifier)
        }
        is UiHttpResponse.Failure -> {}
    }
}

@Composable
private fun ResponseSectionData(
    data: UiHttpResponse.Data,
    modifier: Modifier = Modifier
) {
    var currentSelectedTab by retain { mutableStateOf(ResponseViewerTab.Body) }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Response",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "${data.timeTaken} ms",
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        }

        Spacer(Modifier.height(16.dp))

        ResponseStatusChip(
            status = data.status,
            statusCode = data.statusCode,
            modifier = Modifier
                .widthIn(max = 300.dp),
        )

        ResponseViewerTabs(
            selectedTab = currentSelectedTab,
            onViewerTabSelected = { currentSelectedTab = it },
        )

        AnimatedContent(
            currentSelectedTab
        ) {
            when(it) {
                ResponseViewerTab.Body -> {
                    ResponseContentViewer(
                        content = data.content,
                        contentType = data.contentType,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                ResponseViewerTab.Cookies -> {
                    Text("Cookies")
                }
                ResponseViewerTab.Headers -> {
                    ResponseHeaderViewer(
                        headers = data.responseHeaders.map { h -> h.key to h.value.joinToString(";") }.toMap(),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun ResponseStatusChip(
    status: UiHttpStatus,
    statusCode: UiHttpStatusCode,
    modifier: Modifier = Modifier
) {
    val color = when(status) {
        UiHttpStatus.SUCCESS -> ColorToken.Green500.color
        UiHttpStatus.FAILURE -> ColorToken.Red400.color
        UiHttpStatus.BASIC -> ColorToken.Teal500.color
        UiHttpStatus.INTERMEDIATE -> ColorToken.Pink500.color
        UiHttpStatus.UNKNOWN -> ColorToken.Yellow500.color
    }

    val contentColor = when(status) {
        UiHttpStatus.SUCCESS -> ColorToken.Green500.contentColor
        UiHttpStatus.FAILURE -> ColorToken.Red400.contentColor
        UiHttpStatus.BASIC -> ColorToken.Teal500.contentColor
        UiHttpStatus.INTERMEDIATE -> ColorToken.Pink500.contentColor
        UiHttpStatus.UNKNOWN -> ColorToken.Yellow500.contentColor
    }

    Row(
        modifier = modifier
            .background(color = color, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor
        ) {
            Text(
                text = statusCode.code.toString(),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = statusCode.text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}