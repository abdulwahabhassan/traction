package com.traction.core.data.util

import com.traction.core.common.model.Result

//fun <T> handleResponse(
//    requestResult: Result<T>
//): Result<T> = when (requestResult) {
//    is Result.Success -> {
//        if (requestResult.data.successful == true && requestResult.data.hasResult == true)
//            Result.Success(data = requestResult.data.result!!)
//        else Result.Error(
//            message = requestResult.data.message ?: "Something went wrong! We're fixing it!"
//        )
//    }
//
//    is Result.Error -> {
//        Result.Error(requestResult.message)
//    }
//
//}