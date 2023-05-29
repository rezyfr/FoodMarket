package com.rezyfr.foodmarket.data.auth.repository

import com.rezyfr.foodmarket.core.network.util.handleResponse
import com.rezyfr.foodmarket.data.auth.AuthService
import com.rezyfr.foodmarket.data.auth.model.SignUpRequest.Companion.fromSignupParams
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams
import com.rezyfr.foodmarket.domain.auth.model.SignUpResult
import com.rezyfr.foodmarket.domain.auth.repository.AuthRepository

class AuthRepositoryImpl(
    private val authService: AuthService
): AuthRepository {
    override suspend fun register(param: SignUpParams): SignUpResult {
        val response = authService.register(param.fromSignupParams()).handleResponse()
        return response!!.mapToSignUpResult()
    }
}