package com.rezyfr.foodmarket.domain.auth.repository

import com.rezyfr.foodmarket.core.domain.model.ErrorResponse
import com.rezyfr.foodmarket.core.domain.model.NetworkResponse
import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.utils.Either
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams
import com.rezyfr.foodmarket.domain.auth.model.SignUpResult

interface AuthRepository {
    fun register(param: SignUpParams): SignUpResult
}