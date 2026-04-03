package com.techullurgy.dosthman.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RequestResponseDao {

    @Query("SELECT * FROM request_response")
    fun getAllRequestResponses(): Flow<List<RequestResponseEntity>>

    @Query("SELECT * FROM request_response WHERE requestId = :requestId")
    suspend fun findByRequestId(requestId: String): RequestResponseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(data: RequestResponseEntity)

    @Delete
    suspend fun delete(data: RequestResponseEntity)
}