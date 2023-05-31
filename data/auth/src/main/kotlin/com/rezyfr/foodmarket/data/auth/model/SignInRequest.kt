package com.rezyfr.foodmarket.data.auth.model

import com.google.gson.annotations.SerializedName
import com.rezyfr.foodmarket.domain.auth.model.SignInParams
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams

data class SignInRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
) {
    companion object {
        fun SignInParams.fromSigninParams(): SignInRequest {
            return SignInRequest(
                email = email,
                password = password,
            )
        }
    }
}
