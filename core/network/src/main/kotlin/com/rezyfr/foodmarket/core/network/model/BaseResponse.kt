package com.rezyfr.foodmarket.core.network.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("meta")
    val meta: MetaResponse?,
    @SerializedName("data")
    val result: T?
)

data class MetaResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
