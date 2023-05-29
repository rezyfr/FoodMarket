package com.rezyfr.foodmarket.core.network.model

data class ErrorResponse(
    val status: String? = null,
    val code: Int? = null,
    val message: String? = null,
    val warning: String? = null
)