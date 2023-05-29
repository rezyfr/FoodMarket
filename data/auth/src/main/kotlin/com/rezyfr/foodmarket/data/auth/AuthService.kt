package com.rezyfr.foodmarket.data.auth

import com.rezyfr.foodmarket.core.domain.model.ErrorResponse
import com.rezyfr.foodmarket.core.domain.model.NetworkResponse
import com.rezyfr.foodmarket.data.auth.model.SignUpRequest
import com.rezyfr.foodmarket.data.auth.model.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("register")
    fun register(
        @Body body: SignUpRequest
    ): NetworkResponse<SignUpResponse, ErrorResponse>
}