package com.techullurgy.dosthman.data.utils

import com.techullurgy.dosthman.domain.models.AppHttpMethod
import com.techullurgy.dosthman.domain.models.AppHttpResponse
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.polymorphic

fun SerializersModuleBuilder.provideAppHttpMethodSerializersModule() {
    polymorphic(AppHttpMethod::class) {
        subclass(AppHttpMethod.Get::class, AppHttpMethod.Get.serializer())
        subclass(AppHttpMethod.Post::class, AppHttpMethod.Post.serializer())
        subclass(AppHttpMethod.Put::class, AppHttpMethod.Put.serializer())
        subclass(AppHttpMethod.Delete::class, AppHttpMethod.Delete.serializer())
        subclass(AppHttpMethod.Options::class, AppHttpMethod.Options.serializer())
        subclass(AppHttpMethod.Head::class, AppHttpMethod.Head.serializer())
        subclass(AppHttpMethod.Patch::class, AppHttpMethod.Patch.serializer())
    }
}

fun SerializersModuleBuilder.provideAppHttpResponseSerializersModule() {
    polymorphic(AppHttpResponse::class) {
        subclass(AppHttpResponse.Data::class, AppHttpResponse.Data.serializer())
        subclass(AppHttpResponse.Failure::class, AppHttpResponse.Failure.serializer())
    }
}