package com.rezyfr.foodmarket.domain.order.repository

import com.rezyfr.foodmarket.domain.order.model.FoodOrderModel

interface OrderRepository {
    suspend fun getFoodOrder(state: String) : List<FoodOrderModel>
}