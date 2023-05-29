package com.rezyfr.foodmarket.feature.auth.signup

import com.rezyfr.foodmarket.core.ui.base.BaseFlowViewModel
import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.domain.utils.Either
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() :
    BaseFlowViewModel<SignUpViewState, SignUpViewEvent>() {

    private var signUpParams = MutableStateFlow(initialUi.params)
    private var signUpResult = MutableStateFlow(initialUi.result)
    override val initialUi: SignUpViewState
        get() = SignUpViewState()
    override val uiFlow: Flow<SignUpViewState>
        get() = combine(signUpParams, signUpResult) { params, result ->
            SignUpViewState(
                params = params,
                isValidate = params.email.isNotEmpty() && params.password.isNotEmpty() && params.name.isNotEmpty(),
                result = result
            )
        }

    private fun onEmailChanged(email: String) {
        signUpParams.value = signUpParams.value.copy(email = email)
    }
    private fun onPasswordChanged(password: String) {
        signUpParams.value = signUpParams.value.copy(password = password)
    }
    private fun onNameChanged(name: String) {
        signUpParams.value = signUpParams.value.copy(name = name)
    }

    override suspend fun handleEvent(event: SignUpViewEvent) {
        when (event) {
            is SignUpViewEvent.OnEmailChanged -> {
                onEmailChanged(event.email)
            }

            is SignUpViewEvent.OnPasswordChanged -> {
                onPasswordChanged(event.password)
            }

            is SignUpViewEvent.OnNameChanged -> {
                onNameChanged(event.name)
            }
        }
    }
}

data class SignUpViewState(
    val params: SignUpParams = SignUpParams("", "", "", "", "", "", ""),
    val isValidate: Boolean = false,
    val result: ViewResult<Unit> = ViewResult.Uninitialized,
)

sealed interface SignUpViewEvent {
    data class OnEmailChanged(val email: String) : SignUpViewEvent
    data class OnPasswordChanged(val password: String) : SignUpViewEvent
    data class OnNameChanged(val name: String) : SignUpViewEvent
}