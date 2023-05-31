package com.rezyfr.foodmarket.data.auth.repository

import com.rezyfr.foodmarket.core.network.util.handleResponse
import com.rezyfr.foodmarket.core.persistence.source.DataStoreSource
import com.rezyfr.foodmarket.core.persistence.source.DataStoreSourceImpl
import com.rezyfr.foodmarket.data.auth.AuthService
import com.rezyfr.foodmarket.data.auth.model.SignInRequest.Companion.fromSigninParams
import com.rezyfr.foodmarket.data.auth.model.SignUpRequest.Companion.fromSignupParams
import com.rezyfr.foodmarket.domain.auth.model.SignInParams
import com.rezyfr.foodmarket.domain.auth.model.SignInResult
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams
import com.rezyfr.foodmarket.domain.auth.model.SignUpResult
import com.rezyfr.foodmarket.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val dataStore: DataStoreSource
) : AuthRepository {
    override suspend fun register(param: SignUpParams): SignUpResult {
        val response = authService.register(param.fromSignupParams()).handleResponse()
        return response!!.mapToSignUpResult()
    }

    override suspend fun login(param: SignInParams): SignInResult {
        val response = authService.login(param.fromSigninParams()).handleResponse()
        return response!!.mapToSignInResult()
    }
    override suspend fun saveToken(token: String) {
        dataStore.saveToken(token)
    }
    override fun getToken(): Flow<String?> {
        return dataStore.getToken()
    }
}