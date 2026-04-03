package com.techullurgy.dosthman.data.room

import androidx.room.TypeConverter
import com.techullurgy.dosthman.data.utils.provideAppHttpMethodSerializersModule
import com.techullurgy.dosthman.data.utils.provideAppHttpResponseSerializersModule
import com.techullurgy.dosthman.domain.models.PersistentRequestResponse
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

internal class PersistentRequestResponseTypeConverter {
    private val json = Json {
        serializersModule = SerializersModule {
            provideAppHttpMethodSerializersModule()
            provideAppHttpResponseSerializersModule()
        }
    }

    @TypeConverter
    fun persistenceRequestResponseToString(data: PersistentRequestResponse): String {
        return json.encodeToString(data)
    }

    @TypeConverter
    fun stringToPersistenceRequestResponse(data: String): PersistentRequestResponse {
        return json.decodeFromString(data)
    }
}