package com.techullurgy.dosthman.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.techullurgy.dosthman.domain.models.PersistentRequestResponse

@Entity(tableName = "request_response")
data class RequestResponseEntity(
    @PrimaryKey val requestId: String,
    val json: PersistentRequestResponse
)
