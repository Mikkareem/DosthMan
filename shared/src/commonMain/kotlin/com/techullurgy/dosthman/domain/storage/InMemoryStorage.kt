package com.techullurgy.dosthman.domain.storage

import kotlinx.coroutines.flow.Flow

interface InMemoryStorage<T> {
    val data: Flow<Map<String, T>>

    fun save(key: String, data: T)
    fun findByKey(key: String): T?

    fun remove(key: String)
}