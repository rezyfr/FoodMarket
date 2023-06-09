package com.rezyfr.foodmarket.feature.order.model
data class OrderParams(
    val foodId: String,
    val quantity: Int,
    val total: Double,
)