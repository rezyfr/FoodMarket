package com.rezyfr.foodmarket.data.auth

import com.rezyfr.foodmarket.core.network.model.ErrorResponse
import com.rezyfr.foodmarket.core.domain.model.NetworkResponse
import com.rezyfr.foodmarket.core.network.model.BaseResponse
import com.rezyfr.foodmarket.data.auth.model.SignInRequest
import com.rezyfr.foodmarket.data.auth.model.SignInResponse
import com.rezyfr.foodmarket.data.auth.model.SignUpRequest
import com.rezyfr.foodmarket.data.auth.model.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("register")
    suspend fun register(
        @Body body: SignUpRequest
    ): NetworkResponse<BaseResponse<SignUpResponse>, ErrorResponse>

    @POST("login")
    suspend fun login(
        @Body body: SignInRequest
    ): NetworkResponse<BaseResponse<SignInResponse>, ErrorResponse>
}