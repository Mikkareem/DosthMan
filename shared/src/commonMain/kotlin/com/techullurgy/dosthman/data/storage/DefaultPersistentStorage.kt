package com.techullurgy.dosthman.data.storage

import com.techullurgy.dosthman.data.room.RequestResponseDao
import com.techullurgy.dosthman.data.room.RequestResponseEntity
import com.techullurgy.dosthman.domain.models.PersistentRequestResponse
import com.techullurgy.dosthman.domain.storage.PersistentStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single(binds = [PersistentStorage::class])
internal class DefaultPersistentStorage(
    private val requestResponseDao: RequestResponseDao
): PersistentStorage<PersistentRequestResponse> {

    override val data: Flow<Map<String, PersistentRequestResponse>> = requestResponseDao.getAllRequestResponses().map {
        it
            .map {
                PersistentRequestResponse(
                    id = it.requestId,
                    request = it.json.request,
                    response = it.json.response
                )
            }
            .associateBy { f -> f.id }
    }

    override suspend fun findByKey(key: String): PersistentRequestResponse? {
        return requestResponseDao.findByRequestId(key)?.let {
            PersistentRequestResponse(
                id = it.requestId,
                request = it.json.request,
                response = it.json.response
            )
        }
    }

    override suspend fun save(
        key: String,
        data: PersistentRequestResponse
    ) {
        requestResponseDao.save(
            RequestResponseEntity(
                requestId = key,
                json = PersistentRequestResponse(
                    id = data.id,
                    request = data.request,
                    response = data.response
                )
            )
        )
    }
}