package com.techullurgy.dosthman.presentation.screens.sections

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.techullurgy.dosthman.presentation.components.RequestBuilderTabs
import com.techullurgy.dosthman.presentation.components.RequestHeadersBuilder
import com.techullurgy.dosthman.presentation.components.RequestMethodInput
import com.techullurgy.dosthman.presentation.components.RequestParamsBuilder
import com.techullurgy.dosthman.presentation.components.RequestSendButton
import com.techullurgy.dosthman.presentation.components.RequestUrlInput
import com.techullurgy.dosthman.presentation.models.RequestBuilderTab
import com.techullurgy.dosthman.presentation.models.UiHttpMethod
import com.techullurgy.dosthman.presentation.models.UiHttpRequest
import com.techullurgy.dosthman.presentation.utils.LocalWindowSizeMode
import com.techullurgy.dosthman.presentation.utils.WindowSizeMode

@Composable
internal fun RequestSection(
    request: UiHttpRequest,
    urlState: TextFieldState,
    paramsState: List<Pair<TextFieldState, TextFieldState>>,
    headersState: List<Pair<TextFieldState, TextFieldState>>,
    onMethodChange: (UiHttpMethod) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {

        val windowMode = LocalWindowSizeMode.current

        when(windowMode) {
            WindowSizeMode.MOBILE_PORTRAIT -> {
                RequestUrlInput(
                    urlState = urlState,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RequestMethodInput(
                        method = request.method,
                        onMethodChange = onMethodChange,
                    )

                    RequestSendButton(
                        onSendClick = onSendClick
                    )
                }
            }
            else -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    RequestMethodInput(
                        method = request.method,
                        onMethodChange = onMethodChange,
                    )

                    RequestUrlInput(
                        urlState = urlState,
                        modifier = Modifier.weight(1f)
                    )

                    RequestSendButton(
                        onSendClick = onSendClick
                    )
                }
            }
        }

        val builderTabs = retain {
            listOf(
                RequestBuilderTab.Params(),
                RequestBuilderTab.Authorization(),
                RequestBuilderTab.Headers(),
                RequestBuilderTab.Body()
            ).toMutableStateList()
        }

        var selectedBuilderTabIndex by remember { mutableIntStateOf(0) }

        LaunchedEffect(request.params.count()) {
            val paramsIndex = builderTabs.indexOfFirst { it is RequestBuilderTab.Params }
            if(paramsIndex != -1) {
                builderTabs[paramsIndex] = (builderTabs[paramsIndex] as RequestBuilderTab.Params)
                    .copy(count = request.params.count())
            }
        }

        LaunchedEffect(request.headers.count()) {
            val headersIndex = builderTabs.indexOfFirst { it is RequestBuilderTab.Headers }
            if(headersIndex != -1) {
                builderTabs[headersIndex] = (builderTabs[headersIndex] as RequestBuilderTab.Headers)
                    .copy(count = request.headers.count())
            }
        }

        RequestBuilderTabs(
            builderTabs = builderTabs,
            selectedBuilderTab = builderTabs[selectedBuilderTabIndex],
            onBuilderTabSelected = { selectedBuilderTabIndex = builderTabs.indexOf(it) }
        )

        AnimatedContent(
            targetState = builderTabs[selectedBuilderTabIndex]
        ) { builderTab ->
            when(builderTab) {
                is RequestBuilderTab.Authorization -> {
                    Text("Authorization")
                }
                is RequestBuilderTab.Body -> {
                    Text("Body")
                }
                is RequestBuilderTab.Headers -> {
                    RequestHeadersBuilder(
                        headersState = headersState
                    )
                }
                is RequestBuilderTab.Params -> {
                    RequestParamsBuilder(
                        paramsState = paramsState
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RequestSectionPreview(
    @PreviewParameter(RequestSectionDataParameterProvider::class) data: RequestSectionData
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        RequestSection(
            request = data.request,
            urlState = TextFieldState(),
            paramsState = emptyList(),
            headersState = emptyList(),
            onMethodChange = {},
            onSendClick = {}
        )
    }
}

private data class RequestSectionData(
    val request: UiHttpRequest
)

private class RequestSectionDataParameterProvider: PreviewParameterProvider<RequestSectionData> {
    override val values: Sequence<RequestSectionData> = sequenceOf(
        RequestSectionData(
            request = UiHttpRequest(
                requestKey = "",
                method = UiHttpMethod.Options,
                url = "https://localhost:8080",
                name = null,
                params = listOf(
                    "name" to "Irsath Kareem",
                    "age" to "34"
                )
            )
        ),
        RequestSectionData(
            request = UiHttpRequest(
                requestKey = "",
                method = UiHttpMethod.Post,
                url = "https://localhost:8080",
                name = null,
                params = listOf(
                    "name" to "Riyas",
                    "age" to "27"
                )
            )
        )
    )
}