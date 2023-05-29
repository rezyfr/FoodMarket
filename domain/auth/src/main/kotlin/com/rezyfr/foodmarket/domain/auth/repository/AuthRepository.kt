package com.rezyfr.foodmarket.domain.auth.repository

import com.rezyfr.foodmarket.domain.auth.model.SignUpParams
import com.rezyfr.foodmarket.domain.auth.model.SignUpResult

interface AuthRepository {
    suspend fun register(param: SignUpParams): SignUpResult
}