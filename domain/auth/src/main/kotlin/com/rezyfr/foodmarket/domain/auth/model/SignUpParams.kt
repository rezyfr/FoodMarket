package com.rezyfr.foodmarket.domain.auth.model

data class SignUpParams(
    val name: String,
    val email: String,
    val password: String,
//    val imagePath: String,
    val phoneNumber: String,
    val address: String,
    val houseNumber: String,
    val city: String
)
