package com.rezyfr.foodmarket.domain.food.repository

import com.rezyfr.foodmarket.domain.food.model.FoodModel

interface FoodRepository {
    suspend fun getFoods(
        /** Query Params
            limit: Int,
            name: String,
            types: String,
            priceFrom: Long,
            priceTo: Long,
            rateFrom: Int,
            rateTo: Int,
        **/
    ): List<FoodModel>
}