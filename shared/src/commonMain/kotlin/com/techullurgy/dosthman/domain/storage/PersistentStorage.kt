package com.techullurgy.dosthman.domain.storage

import kotlinx.coroutines.flow.Flow

interface PersistentStorage<T> {
    val data: Flow<Map<String, T>>

    suspend fun save(key: String, data: T)
    suspend fun findByKey(key: String): T?
}