package com.rezyfr.foodmarket.domain.order.model

data class PaymentDetails(
    val tax: Long = 0,
    val totalAmount: Long = 0,
    val driverFee: Long = 0
)