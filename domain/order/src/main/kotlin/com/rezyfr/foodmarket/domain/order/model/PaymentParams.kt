package com.rezyfr.foodmarket.domain.order.model

import java.io.Serializable

/**
    'food_id' => 'required|exists:food,id',
    'user_id' => 'required|exists:users,id',
    'quantity' => 'required',
    'total' => 'required',
    'status' => 'required',
 **/

data class PaymentParams(
    val foodId: String,
    val userId: String,
    val quantity: Int,
    val total: Int,
    val foodPrice: Int,
    val status: String
) : Serializable {
    companion object {
        fun empty() = PaymentParams(
            foodId = "",
            userId = "",
            quantity = 0,
            total = 0,
            foodPrice = 0,
            status = ""
        )
    }
}
