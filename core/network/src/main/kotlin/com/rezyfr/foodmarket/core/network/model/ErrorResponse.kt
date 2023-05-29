package com.rezyfr.foodmarket.core.network.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("meta")
    val error: MetaResponse?,
)