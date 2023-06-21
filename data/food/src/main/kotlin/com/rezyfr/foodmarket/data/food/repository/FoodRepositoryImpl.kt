package com.rezyfr.foodmarket.data.food.repository

import com.rezyfr.foodmarket.core.domain.model.PagingResult
import com.rezyfr.foodmarket.core.network.model.PagingResponse.Companion.mapToPagingResult
import com.rezyfr.foodmarket.core.network.util.handleResponse
import com.rezyfr.foodmarket.data.food.FoodService
import com.rezyfr.foodmarket.data.food.model.FoodResponse.Companion.mapToFoodModel
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.domain.food.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

class FoodRepositoryImpl(
    private val foodService: FoodService
) : FoodRepository {
    override suspend fun getFoods(page: Int): Flow<PagingResult<FoodModel>> = flow {
        val response = foodService.getFoods(page).handleResponse() ?: throw Exception("Data is empty")
        val data = response.mapToPagingResult { it.mapToFoodModel() }
        emit(data)
    }.flowOn(Dispatchers.IO)

    override suspend fun getFoodById(id: String): FoodModel {
        return FoodModel.getDummy().first { it.id == id }
    }
}