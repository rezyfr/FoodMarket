package com.rezyfr.foodmarket.domain.auth.repository

import com.rezyfr.foodmarket.domain.auth.model.SignInParams
import com.rezyfr.foodmarket.domain.auth.model.SignInResult
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams
import com.rezyfr.foodmarket.domain.auth.model.SignUpResult
import com.rezyfr.foodmarket.domain.auth.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(param: SignUpParams): SignUpResult
    suspend fun login(param: SignInParams): SignInResult
    suspend fun saveToken(token: String)
    fun getToken(): Flow<String?>
    suspend fun saveUser(user: UserDomainModel)
    fun getUser(): Flow<UserDomainModel?>
}