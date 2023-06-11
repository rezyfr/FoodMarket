package com.rezyfr.foodmarket.data.order.repository

import com.rezyfr.foodmarket.domain.order.model.FoodOrderModel
import com.rezyfr.foodmarket.domain.order.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor() : OrderRepository {
    override suspend fun getFoodOrder(state: String): List<FoodOrderModel> {
        val dummyData = FoodOrderModel.dummy()
        return if (state == "ongoing") dummyData.dropLast(1)
        else dummyData.drop(2)
    }
}