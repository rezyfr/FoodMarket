package com.rezyfr.foodmarket.feature.order.food

import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.domain.order.model.PaymentParams
import com.rezyfr.foodmarket.feature.order.model.OrderParams

sealed interface FoodDetailEvent {
    data class ChangeQty(val isAdd: Boolean) : FoodDetailEvent
    object OnOrderClicked : FoodDetailEvent
}

data class FoodDetailViewState(
    val food: ViewResult<FoodModel> = ViewResult.Uninitialized,
    val orderParams: OrderParams = OrderParams("", 0, 0.0)
)

sealed class FoodDetailViewEffect {
    data class OpenPaymentScreen(
        val data: PaymentParams,
        val foodName: String,
        val foodImage: String
    ) : FoodDetailViewEffect()
}
