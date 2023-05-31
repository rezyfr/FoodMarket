package com.rezyfr.foodmarket.data.auth.model


import com.google.gson.annotations.SerializedName
import com.rezyfr.foodmarket.domain.auth.model.SignInResult
import com.rezyfr.foodmarket.domain.auth.model.UserDomainModel

data class SignInResponse(
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
        @SerializedName("current_team_id")
        val currentTeamId: String?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("email_verified_at")
        val emailVerifiedAt: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("profile_photo_path")
        val profilePhotoPath: String?,
        @SerializedName("profile_photo_url")
        val profilePhotoUrl: String?,
        @SerializedName("updated_at")
        val updatedAt: String?
    )

    fun mapToSignInResult() = SignInResult(
        token = accessToken.orEmpty(),
        user = UserDomainModel(
            createdAt = user?.createdAt.orEmpty(),
            currentTeamId = user?.currentTeamId.orEmpty(),
            email = user?.email.orEmpty(),
            emailVerifiedAt = user?.emailVerifiedAt.orEmpty(),
            id = user?.id ?: 0,
            name = user?.name.orEmpty(),
            profilePhotoPath = user?.profilePhotoPath.orEmpty(),
            profilePhotoUrl = user?.profilePhotoUrl.orEmpty(),
            updatedAt = user?.updatedAt.orEmpty(),
        )
    )
}