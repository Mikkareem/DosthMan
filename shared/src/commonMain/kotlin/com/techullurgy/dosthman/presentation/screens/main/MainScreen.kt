package com.techullurgy.dosthman.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techullurgy.dosthman.presentation.components.AnimatingContainer
import com.techullurgy.dosthman.presentation.components.CreateNewRequestButton
import com.techullurgy.dosthman.presentation.components.TabsColumn
import com.techullurgy.dosthman.presentation.components.root.AppEditableText
import com.techullurgy.dosthman.presentation.models.Tab
import com.techullurgy.dosthman.presentation.models.UiContentType
import com.techullurgy.dosthman.presentation.models.UiHttpMethod
import com.techullurgy.dosthman.presentation.models.UiHttpRequest
import com.techullurgy.dosthman.presentation.models.UiHttpResponse
import com.techullurgy.dosthman.presentation.models.UiHttpStatus
import com.techullurgy.dosthman.presentation.models.UiHttpStatusCode
import com.techullurgy.dosthman.presentation.models.UiRequestResponse
import com.techullurgy.dosthman.presentation.screens.tab.LocalInspectionTabState
import com.techullurgy.dosthman.presentation.screens.tab.TabScreen
import com.techullurgy.dosthman.presentation.screens.tab.TabState
import com.techullurgy.dosthman.presentation.utils.LocalWindowSizeMode
import com.techullurgy.dosthman.presentation.utils.WindowSizeMode
import com.techullurgy.dosthman.presentation.utils.theme.DosthManTheme
import com.techullurgy.dosthman.presentation.utils.verticalSafeDrawingPadding
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen() {
    val viewModel = koinViewModel<MainViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()

    val eventFlowProvider = remember {
        { viewModel.eventChannel }
    }

    MainScreenImpl(
        state = state,
        eventFlowProvider = eventFlowProvider,
        onAction = viewModel::onAction
    )
}

@Composable
private fun MainScreenImpl(
    state: MainState,
    // TODO: Change to () -> Flow<*> backed by SharedFlow, because Channel is (consumed once (or) One of the Collector receives value)
    eventFlowProvider: () -> Channel<MainEvent>,
    onAction: (MainAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {

        val windowMode = LocalWindowSizeMode.current

        when(windowMode) {
            WindowSizeMode.MOBILE_PORTRAIT -> {
                MainScreenImplSectionForMobilePortrait(
                    tabs = state.tabs,
                    selectedTab = state.selectedTab,
                    eventFlowProvider = eventFlowProvider,
                    onCreateNewRequestClick = { onAction(MainAction.OnCreateNewRequestTriggered) },
                    onTabSelected = { onAction(MainAction.OnTabSelected(it)) },
                    onTabNameChanged = { id, name ->
                        onAction(MainAction.OnTabNameChanged(id, name))
                    }
                )
            }
            else -> {
                MainScreenImplSectionForDesktop(
                    tabs = state.tabs,
                    selectedTab = state.selectedTab,
                    onCreateNewRequestClick = { onAction(MainAction.OnCreateNewRequestTriggered) },
                    onTabSelected = { onAction(MainAction.OnTabSelected(it)) },
                    onTabNameChanged = { id, name ->
                        onAction(MainAction.OnTabNameChanged(id, name))
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalSafeDrawingPadding()
                )
            }
        }
    }
}

@Composable
private fun MainScreenImplSectionForMobilePortrait(
    tabs: List<Tab>,
    selectedTab: String?,
    eventFlowProvider: () -> Channel<MainEvent>,
    onCreateNewRequestClick: () -> Unit,
    onTabSelected: (String) -> Unit,
    onTabNameChanged: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val latestEventFlowProvider by rememberUpdatedState(eventFlowProvider)

    var isTabsColumnOpen by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        latestEventFlowProvider()
            .receiveAsFlow()
            .filterIsInstance<MainEvent.TabChangeEvent>()
            .collectLatest {
                isTabsColumnOpen = false
            }
    }

    Box(
        modifier = modifier
    ) {
        if(tabs.isEmpty()) {
            Box(
                Modifier.fillMaxSize(),
                Alignment.Center
            ) {
                CreateNewRequestButton(onClick = onCreateNewRequestClick)
            }
        } else {
            TabsColumn(
                tabs = tabs,
                selectedTab = selectedTab,
                onCreateNewRequestClick = onCreateNewRequestClick,
                onTabSelected = onTabSelected,
                onTabNameChanged = onTabNameChanged,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .fillMaxHeight()
                    .verticalSafeDrawingPadding()
                    .padding(16.dp)
            )
        }

        selectedTab?.let { tabKey ->
            AnimatingContainer(
                isOpen = isTabsColumnOpen,
                onIsOpenChange = { isOpen -> isTabsColumnOpen = isOpen },
                modifier = Modifier
                    .fillMaxSize()
                    .verticalSafeDrawingPadding()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            painter = object : Painter() {
                                override fun DrawScope.onDraw() { drawRect(color = Color.Black) }
                                override val intrinsicSize: Size = Size.Unspecified
                            },
                            contentDescription = null,
                            tint = Color.Green,
                            modifier = Modifier.clickable {
                                isTabsColumnOpen = !isTabsColumnOpen
                            }
                        )

                        AppEditableText(
                            text = tabs.first { t -> t.tabId == tabKey }.tabName,
                            onTextChange = { onTabNameChanged(tabKey, it) },
                            textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.weight(1f),
                        )
                    }

                    TabScreen(
                        tabKey = tabKey,
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun MainScreenImplSectionForDesktop(
    tabs: List<Tab>,
    selectedTab: String?,
    onCreateNewRequestClick: () -> Unit,
    onTabSelected: (String) -> Unit,
    onTabNameChanged: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        TabsColumn(
            tabs = tabs,
            selectedTab = selectedTab,
            onCreateNewRequestClick = onCreateNewRequestClick,
            onTabSelected = onTabSelected,
            onTabNameChanged = onTabNameChanged,
            modifier = Modifier
                .widthIn(max = 200.dp)
                .fillMaxHeight()
        )

        if(selectedTab == null) {
            Box(
                Modifier.weight(1f).fillMaxHeight(),
                Alignment.Center
            ) {
                CreateNewRequestButton(onClick = onCreateNewRequestClick)
            }
        } else {
            TabScreen(
                tabKey = selectedTab,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    DosthManTheme {
        CompositionLocalProvider(
            LocalInspectionTabState provides TabState(
                requestResponse = UiRequestResponse(
                    requestKey = "123",
                    request = UiHttpRequest(
                        requestKey = "123",
                        method = UiHttpMethod.Get,
                        url = "",
                        name = "Add Two numbers",
                        params = listOf(),
                        headers = listOf()
                    ),
                    response = UiHttpResponse.Data(
                        requestKey = "123",
                        status = UiHttpStatus.SUCCESS,
                        statusCode = UiHttpStatusCode(200, "OK"),
                        requestHeaders = mapOf(),
                        responseHeaders = mapOf(),
                        timeTaken = 829,
                        content = "{}",
                        contentType = UiContentType.ApplicationJson
                    ),
                    isSavePending = true,
                    isLoading = false
                ),
                urlState = TextFieldState("http://localhost:9090"),
                paramsState = listOf(
                    "name" to "Irsath Kareem",
                    "age" to "34"
                ).map { TextFieldState(it.first) to TextFieldState(it.second) }
            )
        ) {
            MainScreenImpl(
                state = MainState(
                    tabs = listOf(
                        Tab("123", "New Request", UiHttpMethod.Get, isSavePending = false),
                        Tab("456", "New Request", UiHttpMethod.Post, isSavePending = true)
                    ),
                    selectedTab = "123",
                    openedTabs = setOf("123")
                ),
                eventFlowProvider = { Channel() },
                onAction = {}
            )
        }
    }
}