package com.rezyfr.foodmarket.data.food

import com.rezyfr.foodmarket.core.domain.model.NetworkResponse
import com.rezyfr.foodmarket.core.network.model.BaseResponse
import com.rezyfr.foodmarket.core.network.model.ErrorResponse
import com.rezyfr.foodmarket.core.network.model.PagingResponse
import com.rezyfr.foodmarket.data.food.model.FoodResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("food")
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
        @Query("page") page: Int
    ): NetworkResponse<BaseResponse<PagingResponse<FoodResponse>>, ErrorResponse>

    @GET("food")
    suspend fun getFoodById(
        @Query("id") id: Int
    ): NetworkResponse<BaseResponse<FoodResponse>, ErrorResponse>
}