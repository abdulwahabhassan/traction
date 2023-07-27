package com.traction.core.data.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import com.traction.core.common.model.Result

suspend fun <T> handleRequest(
    dispatcher: CoroutineDispatcher,
    networkMonitor: NetworkMonitor,
    json: Json,
    apiRequest: suspend () -> T,
): Result<T> = withContext(dispatcher) {
    if (networkMonitor.isOnline.first()::not.invoke()) {
       Result.Error("Check your internet connection!")
    } else {
        try {
            Result.Success(apiRequest.invoke())
        } catch (e: HttpException) {
            val response = handleHttpException(e, json)
            Result.Error(
                message = response?.statusMessage ?: "Something went wrong!"
            )
        } catch (e: Exception) {
            Result.Error(message = e.localizedMessage ?: "Something went wrong!")
        }
    }
}
