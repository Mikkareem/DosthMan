package com.techullurgy.dosthman.presentation.screens.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techullurgy.dosthman.presentation.screens.sections.RequestSection
import com.techullurgy.dosthman.presentation.screens.sections.ResponseSection
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TabScreen(
    tabKey: String,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    DisposableEffect(tabKey) {
        onDispose {
            focusManager.clearFocus()
        }
    }

    val stateProvider  = @Composable {
        if(!LocalInspectionMode.current) {
            val viewModel = koinViewModel<TabViewModel>(
                key = tabKey,
                parameters = {
                    parametersOf(tabKey)
                }
            )

            val state by viewModel.state.collectAsStateWithLifecycle()
            state
        } else {
            LocalInspectionTabState.current
        }
    }

    val onActionProvider = @Composable {
        if(!LocalInspectionMode.current) {
            val viewModel = koinViewModel<TabViewModel>(
                key = tabKey,
                parameters = {
                    parametersOf(tabKey)
                }
            )
            viewModel::onAction
        } else {
            {}
        }
    }

    TabScreenImpl(
        state = stateProvider(),
        onAction = onActionProvider(),
        modifier = modifier
    )
}

@Composable
private fun TabScreenImpl(
    state: TabState,
    onAction: (TabAction) -> Unit,
    modifier: Modifier = Modifier
) {
    state.requestResponse?.let {
        Column(modifier = modifier) {
            RequestSection(
                request = it.request,
                urlState = state.urlState,
                paramsState = state.paramsState,
                headersState = state.headersState,
                onMethodChange = { m -> onAction(TabAction.OnHttpMethodChange(m)) },
                onSendClick = { onAction(TabAction.OnSendRequestTriggered) },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            it.response?.let { resp ->
                ResponseSection(
                    response = resp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }
    }
}