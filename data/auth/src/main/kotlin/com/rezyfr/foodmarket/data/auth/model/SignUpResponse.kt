package com.rezyfr.foodmarket.data.auth.model

import com.google.gson.annotations.SerializedName
import com.rezyfr.foodmarket.domain.auth.model.SignUpResult
import com.rezyfr.foodmarket.domain.auth.model.UserDomainModel

data class SignUpResponse(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("token_type")
    val tokenType: String?,
    @SerializedName("user")
    val user: User?
) {
    data class User(
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("profile_photo_url")
        val profilePhotoUrl: String?,
        @SerializedName("updated_at")
        val updatedAt: String?
    )

    fun mapToSignUpResult() = SignUpResult(
        token = accessToken.orEmpty(),
        user = UserDomainModel(
            createdAt = user?.createdAt.orEmpty(),
            email = user?.email.orEmpty(),
            id = user?.id ?: 0,
            name = user?.name.orEmpty(),
            profilePhotoUrl = user?.profilePhotoUrl.orEmpty(),
            updatedAt = user?.updatedAt.orEmpty(),
        )
    )
}