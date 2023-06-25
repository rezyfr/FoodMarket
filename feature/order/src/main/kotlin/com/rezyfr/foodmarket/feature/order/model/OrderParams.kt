package com.rezyfr.foodmarket.feature.order.model
data class OrderParams(
    val foodId: Int,
    val quantity: Int,
    val total: Double,
)