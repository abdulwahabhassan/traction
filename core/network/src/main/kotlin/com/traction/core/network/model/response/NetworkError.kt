package com.traction.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkError(
    @SerialName("status_message")
    var statusMessage: String?,
    @SerialName("status_code")
    var statusCode: Int?,
    @SerialName("success")
    var success: Boolean?,
)
