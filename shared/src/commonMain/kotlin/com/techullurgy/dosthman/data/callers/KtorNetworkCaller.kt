package com.techullurgy.dosthman.data.callers

import com.techullurgy.dosthman.domain.callers.NetworkCaller
import com.techullurgy.dosthman.domain.models.AppContentType
import com.techullurgy.dosthman.domain.models.AppHttpMethod
import com.techullurgy.dosthman.domain.models.AppHttpRequest
import com.techullurgy.dosthman.domain.models.AppHttpResponse
import com.techullurgy.dosthman.domain.models.AppHttpStatusCode
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.head
import io.ktor.client.request.headers
import io.ktor.client.request.options
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.parameters
import io.ktor.util.toMap
import org.koin.core.annotation.Single

@Single(binds = [NetworkCaller::class])
internal class KtorNetworkCaller(
    private val client: HttpClient
): NetworkCaller {

    override suspend fun sendRequest(request: AppHttpRequest): AppHttpResponse {

        val response = when(request.method) {
            is AppHttpMethod.Delete -> {
                httpDelete(
                    url = request.url,
                    params = request.params,
                    headers = request.headers,
                    body = request.method.body,
                    contentType = request.method.contentType.toContentType()
                )
            }
            is AppHttpMethod.Get -> {
                httpGet(
                    url = request.url,
                    params = request.params,
                    headers = request.headers,
                    body = request.method.body,
                    contentType = request.method.contentType.toContentType()
                )
            }
            is AppHttpMethod.Head -> {
                httpHead(
                    url = request.url,
                    params = request.params,
                    headers = request.headers,
                    body = request.method.body,
                    contentType = request.method.contentType.toContentType()
                )
            }
            is AppHttpMethod.Options -> {
                httpOptions(
                    url = request.url,
                    params = request.params,
                    headers = request.headers,
                    body = request.method.body,
                    contentType = request.method.contentType.toContentType()
                )
            }
            is AppHttpMethod.Patch -> {
                httpPatch(
                    url = request.url,
                    params = request.params,
                    headers = request.headers,
                    body = request.method.body,
                    contentType = request.method.contentType.toContentType()
                )
            }
            is AppHttpMethod.Post -> {
                httpPost(
                    url = request.url,
                    params = request.params,
                    headers = request.headers,
                    body = request.method.body,
                    contentType = request.method.contentType.toContentType()
                )
            }
            is AppHttpMethod.Put -> {
                httpPut(
                    url = request.url,
                    params = request.params,
                    headers = request.headers,
                    body = request.method.body,
                    contentType = request.method.contentType.toContentType()
                )
            }
        }

        return response.let {
            val responseContentType = when {
                it.contentType()?.toString()?.contains(ContentType.Application.Json.toString()) == true -> AppContentType.ApplicationJson
                else -> AppContentType.TextPlain
            }


            when {
//                it.status.isSuccess() -> AppHttpStatus
            }

            AppHttpResponse.Data(
                requestKey = request.requestKey,
                statusCode = it.status.toAppHttpStatusCode(),
                requestHeaders = it.request.headers.toMap(),
                responseHeaders = it.headers.toMap(),
                timeTaken = it.responseTime.timestamp - it.requestTime.timestamp,
                content = it.body(),
                contentType = responseContentType
            )
        }
    }

    private suspend fun httpGet(
        url: String,
        params: Map<String, String>,
        headers: Map<String, String>,
        body: String?,
        contentType: ContentType
    ): HttpResponse {
        return client.get {
            url(url)
            parameters {
                params.forEach { (key, value) ->
                    set(key, value)
                }
            }

            headers {
                headers.forEach { (key, value) ->
                    set(key, value)
                }
            }

            if(body != null) {
                contentType(contentType)
                setBody(body)
            }
        }
    }

    private suspend fun httpPost(
        url: String,
        params: Map<String, String>,
        headers: Map<String, String>,
        body: String?,
        contentType: ContentType
    ): HttpResponse {
        return client.post {
            url(url)
            parameters {
                params.forEach { (key, value) ->
                    set(key, value)
                }
            }

            headers {
                headers.forEach { (key, value) ->
                    set(key, value)
                }
            }

            if(body != null) {
                contentType(contentType)
                setBody(body)
            }
        }
    }

    private suspend fun httpPut(
        url: String,
        params: Map<String, String>,
        headers: Map<String, String>,
        body: String?,
        contentType: ContentType
    ): HttpResponse {
        return client.put {
            url(url)
            parameters {
                params.forEach { (key, value) ->
                    set(key, value)
                }
            }

            headers {
                headers.forEach { (key, value) ->
                    set(key, value)
                }
            }

            if(body != null) {
                contentType(contentType)
                setBody(body)
            }
        }
    }

    private suspend fun httpDelete(
        url: String,
        params: Map<String, String>,
        headers: Map<String, String>,
        body: String?,
        contentType: ContentType
    ): HttpResponse {
        return client.delete {
            url(url)
            parameters {
                params.forEach { (key, value) ->
                    set(key, value)
                }
            }

            headers {
                headers.forEach { (key, value) ->
                    set(key, value)
                }
            }

            if(body != null) {
                contentType(contentType)
                setBody(body)
            }
        }
    }

    private suspend fun httpPatch(
        url: String,
        params: Map<String, String>,
        headers: Map<String, String>,
        body: String?,
        contentType: ContentType
    ): HttpResponse {
        return client.patch {
            url(url)
            parameters {
                params.forEach { (key, value) ->
                    set(key, value)
                }
            }

            headers {
                headers.forEach { (key, value) ->
                    set(key, value)
                }
            }

            if(body != null) {
                contentType(contentType)
                setBody(body)
            }
        }
    }

    private suspend fun httpOptions(
        url: String,
        params: Map<String, String>,
        headers: Map<String, String>,
        body: String?,
        contentType: ContentType
    ): HttpResponse {
        return client.options {
            url(url)
            parameters {
                params.forEach { (key, value) ->
                    set(key, value)
                }
            }

            headers {
                headers.forEach { (key, value) ->
                    set(key, value)
                }
            }

            if(body != null) {
                contentType(contentType)
                setBody(body)
            }
        }
    }

    private suspend fun httpHead(
        url: String,
        params: Map<String, String>,
        headers: Map<String, String>,
        body: String?,
        contentType: ContentType
    ): HttpResponse {
        return client.head {
            url(url)
            parameters {
                params.forEach { (key, value) ->
                    set(key, value)
                }
            }

            headers {
                headers.forEach { (key, value) ->
                    set(key, value)
                }
            }

            if(body != null) {
                contentType(contentType)
                setBody(body)
            }
        }
    }

    private fun AppContentType?.toContentType(): ContentType {
        return this?.let {
            when(it) {
                AppContentType.ApplicationJson -> ContentType.Application.Json
                AppContentType.FormUrlEncoded -> ContentType.Application.FormUrlEncoded
                AppContentType.TextPlain -> ContentType.Text.Plain
            }
        } ?: ContentType.Text.Plain
    }

    private fun HttpStatusCode.toAppHttpStatusCode(): AppHttpStatusCode {
        return when(value) {
            100 -> AppHttpStatusCode.Continue
            101 -> AppHttpStatusCode.SwitchingProtocols
            102 -> AppHttpStatusCode.Processing
            200 -> AppHttpStatusCode.OK
            201 -> AppHttpStatusCode.Created
            202 -> AppHttpStatusCode.Accepted
            203 -> AppHttpStatusCode.NonAuthoritativeInformation
            204 -> AppHttpStatusCode.NoContent
            205 -> AppHttpStatusCode.ResetContent
            206 -> AppHttpStatusCode.PartialContent
            207 -> AppHttpStatusCode.MultiStatus
            300 -> AppHttpStatusCode.MultipleChoices
            301 -> AppHttpStatusCode.MovedPermanently
            302 -> AppHttpStatusCode.Found
            303 -> AppHttpStatusCode.SeeOther
            304 -> AppHttpStatusCode.NotModified
            305 -> AppHttpStatusCode.UseProxy
            306 -> AppHttpStatusCode.SwitchProxy
            307 -> AppHttpStatusCode.TemporaryRedirect
            308 -> AppHttpStatusCode.PermanentRedirect
            400 -> AppHttpStatusCode.BadRequest
            401 -> AppHttpStatusCode.Unauthorized
            402 -> AppHttpStatusCode.PaymentRequired
            403 -> AppHttpStatusCode.Forbidden
            404 -> AppHttpStatusCode.NotFound
            405 -> AppHttpStatusCode.MethodNotAllowed
            406 -> AppHttpStatusCode.NotAcceptable
            407 -> AppHttpStatusCode.ProxyAuthenticationRequired
            408 -> AppHttpStatusCode.RequestTimeout
            409 -> AppHttpStatusCode.Conflict
            410 -> AppHttpStatusCode.Gone
            411 -> AppHttpStatusCode.LengthRequired
            412 -> AppHttpStatusCode.PreconditionFailed
            413 -> AppHttpStatusCode.PayloadTooLarge
            414 -> AppHttpStatusCode.RequestURITooLong
            415 -> AppHttpStatusCode.UnsupportedMediaType
            416 -> AppHttpStatusCode.RequestedRangeNotSatisfiable
            417 -> AppHttpStatusCode.ExpectationFailed
            422 -> AppHttpStatusCode.UnprocessableEntity
            423 -> AppHttpStatusCode.Locked
            424 -> AppHttpStatusCode.FailedDependency
            425 -> AppHttpStatusCode.TooEarly
            426 -> AppHttpStatusCode.UpgradeRequired
            429 -> AppHttpStatusCode.TooManyRequests
            431 -> AppHttpStatusCode.RequestHeaderFieldsTooLarge
            500 -> AppHttpStatusCode.InternalServerError
            501 -> AppHttpStatusCode.NotImplemented
            502 -> AppHttpStatusCode.BadGateway
            503 -> AppHttpStatusCode.ServiceUnavailable
            504 -> AppHttpStatusCode.GatewayTimeout
            505 -> AppHttpStatusCode.HTTPVersionNotSupported
            506 -> AppHttpStatusCode.VariantAlsoNegotiates
            507 -> AppHttpStatusCode.InsufficientStorage
            else -> AppHttpStatusCode.Unknown
        }
    }
}