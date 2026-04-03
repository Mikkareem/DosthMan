package com.techullurgy.dosthman.domain.callers

import com.techullurgy.dosthman.domain.models.AppHttpRequest
import com.techullurgy.dosthman.domain.models.AppHttpResponse

interface NetworkCaller {
    suspend fun sendRequest(request: AppHttpRequest): AppHttpResponse
}