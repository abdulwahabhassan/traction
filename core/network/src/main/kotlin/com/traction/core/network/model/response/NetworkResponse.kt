package com.traction.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse<T>(
    val hasResult: Boolean? = null,
    val result: T? = null,
    val successful: Boolean? = null,
    val resultType: Int? = null,
    val message: String? = null,
    val validationMessages: List<String>? = null,
)