package com.rezyfr.foodmarket.domain.auth.model

data class SignInResult(
    val token: String,
    val user: UserDomainModel
)
