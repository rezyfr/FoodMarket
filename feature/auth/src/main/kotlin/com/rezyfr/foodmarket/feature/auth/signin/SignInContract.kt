package com.rezyfr.foodmarket.feature.auth.signin

import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.domain.auth.model.SignInParams
import com.rezyfr.foodmarket.domain.auth.model.SignInResult

data class SignInViewState(
    val params: SignInParams = SignInParams("", ""),
    val result: ViewResult<SignInResult> = ViewResult.Uninitialized
)

sealed interface SignInViewEvent {
    data class OnEmailChanged(val email: String) : SignInViewEvent
    data class OnPasswordChanged(val password: String) : SignInViewEvent
    object OnSignInClicked : SignInViewEvent
}