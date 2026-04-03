package com.techullurgy.dosthman.domain.models

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppHttpMethod {

    var body: String?
    var contentType: AppContentType?

    @Serializable
    data class Get(
        override var body: String? = null,
        override var contentType: AppContentType? = null
    ): AppHttpMethod

    @Serializable
    data class Post(
        override var body: String? = null,
        override var contentType: AppContentType? = null
    ): AppHttpMethod

    @Serializable
    data class Put(
        override var body: String? = null,
        override var contentType: AppContentType? = null
    ): AppHttpMethod

    @Serializable
    data class Delete(
        override var body: String? = null,
        override var contentType: AppContentType? = null
    ): AppHttpMethod

    @Serializable
    data class Patch(
        override var body: String? = null,
        override var contentType: AppContentType? = null
    ): AppHttpMethod

    @Serializable
    data class Head(
        override var body: String? = null,
        override var contentType: AppContentType? = null
    ): AppHttpMethod

    @Serializable
    data class Options(
        override var body: String? = null,
        override var contentType: AppContentType? = null
    ): AppHttpMethod

}