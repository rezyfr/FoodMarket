package com.rezyfr.foodmarket.domain.food.repository

import com.rezyfr.foodmarket.core.domain.model.PagingResult
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import kotlinx.coroutines.flow.Flow

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
        page: Int
    ):Flow<PagingResult<FoodModel>>
    suspend fun getFoodById(
        id: Int
    ) : FoodModel
}