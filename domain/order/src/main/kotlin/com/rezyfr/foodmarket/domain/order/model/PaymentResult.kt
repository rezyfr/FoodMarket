package com.rezyfr.foodmarket.domain.order.model

/**
 * 'food_id',
 * 'user_id',
 * 'quantity',
 * 'total',
 * 'status',
 * 'payment_url'
 */
data class PaymentResult(
    val foodId: String,
    val userId: String,
    val quantity: Int,
    val total: Int,
    val status: String,
    val paymentUrl: String
)
