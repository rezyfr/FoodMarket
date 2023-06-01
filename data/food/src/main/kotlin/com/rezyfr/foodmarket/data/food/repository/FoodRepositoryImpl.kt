package com.rezyfr.foodmarket.data.food.repository

import com.rezyfr.foodmarket.data.food.FoodService
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.domain.food.repository.FoodRepository
import javax.inject.Inject

class FoodRepositoryImpl (
//    private val foodService: FoodService
) : FoodRepository {
    override suspend fun getFoods(): List<FoodModel> {
//        return foodService.getFoods().handleResponse()?.mapToFoodModel().orEmpty()
        return FoodModel.getDummy()
    }
}