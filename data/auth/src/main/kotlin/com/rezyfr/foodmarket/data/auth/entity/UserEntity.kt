package com.rezyfr.foodmarket.data.auth.entity

data class UserEntity(
    val email: String = "",
    val id: Int = -1,
    val name: String = "",
    val profilePhotoUrl: String = "",
    val profilePhotoPath: String = "",
)