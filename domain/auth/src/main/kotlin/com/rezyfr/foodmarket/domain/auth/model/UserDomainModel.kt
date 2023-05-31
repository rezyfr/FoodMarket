package com.rezyfr.foodmarket.domain.auth.model

data class UserDomainModel(
    val createdAt: String,
    val email: String,
    val id: Int,
    val name: String,
    val profilePhotoUrl: String,
    val updatedAt: String,
    val currentTeamId: String? = null,
    val emailVerifiedAt: String? = null,
    val profilePhotoPath: String? = null,
)