package com.rezyfr.foodmarket.data.auth.model

import com.google.gson.annotations.SerializedName
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams

data class SignUpRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("houseNumber")
    val houseNumber: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("city")
    val city: String
) {
    companion object {

        fun SignUpParams.fromSignupParams(): SignUpRequest {
            return SignUpRequest(
                name = name,
                email = email,
                password = password,
                phoneNumber = phoneNumber,
                address = address,
                houseNumber = houseNumber,
                city = city
            )
        }
    }
}
