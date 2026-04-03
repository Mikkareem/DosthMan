package com.techullurgy.dosthman.data.storage

import com.techullurgy.dosthman.domain.models.SaveableRequestResponse
import com.techullurgy.dosthman.domain.storage.InMemoryStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single

@Single
internal class DefaultInMemoryStorage: InMemoryStorage<SaveableRequestResponse> {
    private val _dataFlow = MutableStateFlow<Map<String, SaveableRequestResponse>>(emptyMap())

    override val data: Flow<Map<String, SaveableRequestResponse>> = _dataFlow.asStateFlow()

    override fun findByKey(key: String): SaveableRequestResponse? {
        return _dataFlow.value[key]
    }

    override fun save(key: String, data: SaveableRequestResponse) {
        _dataFlow.update {
            it + (key to data)
        }
    }

    override fun remove(key: String) {
        _dataFlow.update {
            it - key
        }
    }
}