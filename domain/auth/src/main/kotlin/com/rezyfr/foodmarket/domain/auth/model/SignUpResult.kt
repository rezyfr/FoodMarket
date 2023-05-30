package com.rezyfr.foodmarket.domain.auth.model

data class SignUpResult(
    val token: String,
    val user: UserDomainModel
)
