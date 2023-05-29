package com.rezyfr.foodmarket.data.auth.model

import com.google.gson.annotations.SerializedName
import com.rezyfr.foodmarket.domain.auth.model.SignUpResult
import com.rezyfr.foodmarket.domain.auth.model.UserDomainModel

data class SignUpResponse(
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("result")
    val result: Result?
) {
    data class Meta(
        @SerializedName("code")
        val code: Int?,
        @SerializedName("message")
        val message: String?,
        @SerializedName("status")
        val status: String?
    )

    data class Result(
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
    }

    fun mapToSignUpResult() = SignUpResult(
        token = result?.accessToken.orEmpty(),
        user = UserDomainModel(
            createdAt = result?.user?.createdAt.orEmpty(),
            email = result?.user?.email.orEmpty(),
            id = result?.user?.id ?: 0,
            name = result?.user?.name.orEmpty(),
            profilePhotoUrl = result?.user?.profilePhotoUrl.orEmpty(),
            updatedAt = result?.user?.updatedAt.orEmpty(),
        )
    )
}