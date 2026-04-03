package com.techullurgy.dosthman.domain.models

import kotlinx.serialization.Serializable

@Serializable
enum class AppContentType {

    ApplicationJson, FormUrlEncoded, TextPlain

}