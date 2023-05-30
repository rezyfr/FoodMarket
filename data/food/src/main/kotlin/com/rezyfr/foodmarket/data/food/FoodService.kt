package com.rezyfr.foodmarket.data.food

import com.rezyfr.foodmarket.core.domain.model.NetworkResponse
import com.rezyfr.foodmarket.core.network.model.BaseResponse
import com.rezyfr.foodmarket.core.network.model.ErrorResponse
import com.rezyfr.foodmarket.data.food.model.FoodResponse
import retrofit2.http.GET

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
    ): NetworkResponse<BaseResponse<List<FoodResponse>>, ErrorResponse>
}