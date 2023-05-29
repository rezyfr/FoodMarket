package com.rezyfr.foodmarket.feature.auth.signup

import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.core.ui.base.BaseFlowViewModel
import com.rezyfr.foodmarket.core.domain.model.ViewError
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.domain.utils.Either
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams
import com.rezyfr.foodmarket.domain.auth.model.SignUpResult
import com.rezyfr.foodmarket.domain.auth.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : BaseFlowViewModel<SignUpViewState, SignUpViewEvent>() {
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

    private fun onSignUpClicked() {
        viewModelScope.launch {
            signUpUseCase.invoke(
                SignUpParams(
                    signUpParams.value.name,
                    signUpParams.value.email,
                    signUpParams.value.password,
                    signUpParams.value.phoneNumber,
                    signUpParams.value.address,
                    signUpParams.value.houseNumber,
                    signUpParams.value.city,
                )
            ).collectLatest {
                it.fold(
                    { error -> signUpResult.value = ViewResult.Error(ViewError(error.message)) },
                    { result -> signUpResult.value = ViewResult.Success(result) }
                )
            }
        }
    }

    override suspend fun handleEvent(event: SignUpViewEvent) {
        when (event) {
            is SignUpViewEvent.OnEmailChanged -> onEmailChanged(event.email)
            is SignUpViewEvent.OnPasswordChanged -> onPasswordChanged(event.password)
            is SignUpViewEvent.OnNameChanged -> onNameChanged(event.name)
            SignUpViewEvent.OnSignUpClicked -> onSignUpClicked()
        }
    }
}

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