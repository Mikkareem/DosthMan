@file:OptIn(ExperimentalCoroutinesApi::class)

package com.techullurgy.dosthman.presentation.screens.tab

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techullurgy.dosthman.domain.models.AppHttpMethod
import com.techullurgy.dosthman.domain.usecases.HttpRequestExecutorUsecase
import com.techullurgy.dosthman.domain.usecases.HttpRequestHeadersChangeUsecase
import com.techullurgy.dosthman.domain.usecases.HttpRequestMethodChangeUsecase
import com.techullurgy.dosthman.domain.usecases.HttpRequestParamsChangeUsecase
import com.techullurgy.dosthman.domain.usecases.HttpRequestUrlChangeUsecase
import com.techullurgy.dosthman.domain.usecases.ObserveDataUsecase
import com.techullurgy.dosthman.domain.usecases.SaveRequestUsecase
import com.techullurgy.dosthman.presentation.models.UiHttpMethod
import com.techullurgy.dosthman.presentation.models.toUiRequestResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
internal class TabViewModel(
    @InjectedParam private val tabKey: String,
    private val saveRequestUsecase: SaveRequestUsecase,
    private val httpRequestExecutorUsecase: HttpRequestExecutorUsecase,
    private val httpRequestMethodChangeUsecase: HttpRequestMethodChangeUsecase,
    private val httpRequestUrlChangeUsecase: HttpRequestUrlChangeUsecase,
    private val httpRequestParamsChangeUsecase: HttpRequestParamsChangeUsecase,
    private val httpRequestHeadersChangeUsecase: HttpRequestHeadersChangeUsecase,
    observeDataUsecase: ObserveDataUsecase
): ViewModel() {

    private val _state = MutableStateFlow(TabState())
    val state = _state.asStateFlow()

    private val hasLoadedInitialData = MutableStateFlow(false)

    private val urlFlow = state
        .map { it.urlState }
        .distinctUntilChanged()
        .flatMapLatest {
            snapshotFlow { it.text.toString() }
                .dropWhile { !hasLoadedInitialData.value }.distinctUntilChanged()
        }

    private val paramsFlow = state
        .map { it.paramsState }
        .distinctUntilChanged()
        .flatMapLatest {
            snapshotFlow {
                it.map { pair ->
                    pair.first.text.toString() to pair.second.text.toString()
                }
            }.dropWhile { !hasLoadedInitialData.value }.distinctUntilChanged()
        }

    private val headersFlow = state
        .map { it.headersState }
        .distinctUntilChanged()
        .flatMapLatest {
            snapshotFlow {
                it.map { pair ->
                    pair.first.text.toString() to pair.second.text.toString()
                }
            }.dropWhile { !hasLoadedInitialData.value }.distinctUntilChanged()
        }

    init {
        observeTextFieldStates()

        viewModelScope.launch {
            observeDataUsecase()
                .filter {
                    it[tabKey] != null
                }
                .map {
                    it[tabKey]!!.request
                }
                .first()
                .also { req ->
                    _state.update {
                        it.copy(
                            urlState = TextFieldState(req.url),
                            paramsState = req.params.map {
                                TextFieldState(it.key) to TextFieldState(it.value)
                            }.plus(TextFieldState() to TextFieldState()),
                            headersState = req.headers.map {
                                TextFieldState(it.key) to TextFieldState(it.value)
                            }.plus(TextFieldState() to TextFieldState())
                        )
                    }
                    hasLoadedInitialData.value = true
                }
        }

        observeDataUsecase()
            .filter {
                it[tabKey] != null
            }
            .map {
                it[tabKey]!!
            }
            .onEach {
                _state.update { s ->
                    s.copy(
                        requestResponse = it.toUiRequestResponse()
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: TabAction) {
        when(action) {
            TabAction.OnSaveTriggered -> onSaveTriggered()
            TabAction.OnSendRequestTriggered -> onSendRequestTriggered()
            is TabAction.OnHeadersChange -> onHeadersChange(action)
            is TabAction.OnHttpMethodChange -> onHttpMethodChange(action)
            is TabAction.OnHttpUrlChange -> onHttpUrlChange(action)
            is TabAction.OnParamsChange -> onParamsChange(action)
        }
    }

    private fun onHttpMethodChange(action: TabAction.OnHttpMethodChange) {
        viewModelScope.launch {
            httpRequestMethodChangeUsecase(
                requestKey = tabKey,
                method = when(action.method) {
                    UiHttpMethod.Get -> AppHttpMethod.Get()
                    UiHttpMethod.Post -> AppHttpMethod.Post()
                    UiHttpMethod.Put -> AppHttpMethod.Put()
                    UiHttpMethod.Patch -> AppHttpMethod.Patch()
                    UiHttpMethod.Delete -> AppHttpMethod.Delete()
                    UiHttpMethod.Head -> AppHttpMethod.Head()
                    UiHttpMethod.Options -> AppHttpMethod.Options()
                }
            )
        }
    }

    private fun onHttpUrlChange(action: TabAction.OnHttpUrlChange) {
        viewModelScope.launch {
            httpRequestUrlChangeUsecase(
                requestKey = tabKey,
                url = action.url
            )
        }
    }

    private fun onParamsChange(action: TabAction.OnParamsChange) {
        viewModelScope.launch {
            httpRequestParamsChangeUsecase(
                requestKey = tabKey,
                params = action.params.associate { it.first to it.second }
            )
        }
    }

    private fun onHeadersChange(action: TabAction.OnHeadersChange) {
        viewModelScope.launch {
            httpRequestHeadersChangeUsecase(
                requestKey = tabKey,
                headers = action.headers.associate { it.first to it.second }
            )
        }
    }

    private fun onSaveTriggered() {
        viewModelScope.launch {
            saveRequestUsecase(requestId = tabKey)
        }
    }

    private fun onSendRequestTriggered() {
        httpRequestExecutorUsecase(requestKey = tabKey)
    }

    private fun observeTextFieldStates() {
        urlFlow.onEach { onAction(TabAction.OnHttpUrlChange(it)) }.launchIn(viewModelScope)

        paramsFlow.onEach {
            onAction(TabAction.OnParamsChange(it.filter { k -> k.first.isNotBlank() }))
        }.launchIn(viewModelScope)

        headersFlow.onEach {
            onAction(TabAction.OnHeadersChange(it.filter { k -> k.first.isNotBlank() }))
        }.launchIn(viewModelScope)
    }
}