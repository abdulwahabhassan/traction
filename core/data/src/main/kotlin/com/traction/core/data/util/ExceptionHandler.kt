package com.traction.core.data.util

import com.traction.core.network.model.response.NetworkError
import java.nio.charset.Charset
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.HttpException

fun handleHttpException(e: HttpException, json: Json): NetworkError? {
    return try {
        e.response()?.errorBody()?.source()?.readString(Charset.defaultCharset())?.let  {
            json.decodeFromString<NetworkError>(it)
        }
    } catch (t: Throwable) {
        t.printStackTrace()
        null
    }
}