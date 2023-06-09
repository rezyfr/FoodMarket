package com.rezyfr.foodmarket.feature.auth.signup

import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams
import com.rezyfr.foodmarket.domain.auth.model.SignUpResult

data class SignUpViewState(
    val params: SignUpParams = SignUpParams("", "", "", "", "", "", ""),
    val isValidate: Boolean = false,
    val result: ViewResult<SignUpResult> = ViewResult.Uninitialized,
)

sealed interface SignUpViewEvent {
    data class OnEmailChanged(val email: String) : SignUpViewEvent
    data class OnPasswordChanged(val password: String) : SignUpViewEvent
    data class OnNameChanged(val name: String) : SignUpViewEvent
    object OnSignUpClicked : SignUpViewEvent
}