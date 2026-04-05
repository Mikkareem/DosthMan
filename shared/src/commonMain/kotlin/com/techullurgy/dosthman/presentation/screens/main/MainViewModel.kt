@file:OptIn(ExperimentalUuidApi::class)

package com.techullurgy.dosthman.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techullurgy.dosthman.domain.usecases.ChangeRequestNameUsecase
import com.techullurgy.dosthman.domain.usecases.CreateNewRequestUsecase
import com.techullurgy.dosthman.domain.usecases.ObserveDataUsecase
import com.techullurgy.dosthman.presentation.models.Tab
import com.techullurgy.dosthman.presentation.models.toUiRequestResponse
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@KoinViewModel
internal class MainViewModel(
    private val createNewRequestUsecase: CreateNewRequestUsecase,
    private val changeRequestNameUsecase: ChangeRequestNameUsecase,
    observeDataUsecase: ObserveDataUsecase,
): ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    val eventChannel = Channel<MainEvent>()

    init {
        observeDataUsecase()
            .map {
                it.map { (key, value) ->
                    val uiValue = value.toUiRequestResponse()
                    val tabName = if (!uiValue.request.name.isNullOrBlank()) {
                        uiValue.request.name
                    } else {
                        uiValue.request.url.takeIf { c -> c.isNotBlank() } ?: "New Request"
                    }

                    Tab(
                        tabId = key,
                        tabName = tabName,
                        method = uiValue.request.method,
                        isSavePending = uiValue.isSavePending
                    )
                }
            }
            .onEach { tabs ->
                _state.update {
                    it.copy(
                        tabs = tabs
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: MainAction) {
        when (action) {
            MainAction.OnCreateNewRequestTriggered -> onCreateNewRequestTriggered()
            is MainAction.OnTabSelected -> onTabSelected(action)
            is MainAction.OnTabNameChanged -> onTabNameChanged(action)
        }
    }

    private fun onCreateNewRequestTriggered() {
        val requestId = Uuid.random().toString()
        createNewRequestUsecase(requestId)

        viewModelScope.launch {
            while (true) {
                delay(20)
                if (_state.value.tabs.find { it.tabId == requestId } != null) {
                    _state.update { s ->
                        s.copy(
                            selectedTab = requestId,
                            openedTabs = s.openedTabs + requestId
                        )
                    }
                    break
                }
            }
        }
    }

    private fun onTabSelected(action: MainAction.OnTabSelected) {
        _state.update {
            it.copy(
                selectedTab = action.tabId,
                openedTabs = it.openedTabs + action.tabId
            )
        }
        eventChannel.trySend(MainEvent.TabChangeEvent)
    }

    private fun onTabNameChanged(action: MainAction.OnTabNameChanged) {
        viewModelScope.launch {
            changeRequestNameUsecase(action.tabKey, action.name)
        }
    }
}