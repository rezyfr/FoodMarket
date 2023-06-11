package com.rezyfr.foodmarket.feature.dashboard.order

import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.domain.order.model.FoodOrderModel

data class OrderViewState(
    val onGoingOrders: ViewResult<List<FoodOrderModel>> = ViewResult.Uninitialized,
    val pastOrders: ViewResult<List<FoodOrderModel>> = ViewResult.Uninitialized,
)