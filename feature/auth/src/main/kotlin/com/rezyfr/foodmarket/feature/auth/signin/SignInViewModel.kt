package com.rezyfr.foodmarket.feature.auth.signin

import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.base.BaseFlowViewModel
import com.rezyfr.foodmarket.domain.auth.model.SignInParams
import com.rezyfr.foodmarket.domain.auth.model.SignInResult
import com.rezyfr.foodmarket.domain.auth.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) :
    BaseFlowViewModel<SignInViewState, SignInViewEvent>() {
    private var signInResult = MutableStateFlow(initialUi.result)
    private var signInParams = MutableStateFlow(initialUi.params)
    override val initialUi: SignInViewState
        get() = SignInViewState()
    override val uiFlow: Flow<SignInViewState>
        get() = combine(signInParams, signInResult) { params, result ->
            SignInViewState(
                params = params,
                result = result
            )
        }

    private fun onEmailChanged(email: String) {
        signInParams.value = signInParams.value.copy(email = email)
    }

    private fun onPasswordChanged(password: String) {
        signInParams.value = signInParams.value.copy(password = password)
    }

    private fun signIn() {
        viewModelScope.launch {
            signInUseCase.invoke(
                SignInParams(
                    signInParams.value.email,
                    signInParams.value.password
                )
            ).collectLatest {
                it.fold(
                    { error ->
                        signInResult.value = ViewResult.Error(error)
                    },
                    { result ->
                        signInResult.value = ViewResult.Success(result)
                    }
                )
            }
        }
    }

    override suspend fun handleEvent(event: SignInViewEvent) {
        when (event) {
            is SignInViewEvent.OnEmailChanged -> {
                onEmailChanged(event.email)
            }

            is SignInViewEvent.OnPasswordChanged -> {
                onPasswordChanged(event.password)
            }

            is SignInViewEvent.OnSignInClicked -> {
                signIn()
            }
        }
    }
}

data class SignInViewState(
    val params: SignInParams = SignInParams("", ""),
    val result: ViewResult<SignInResult> = ViewResult.Uninitialized
)

sealed interface SignInViewEvent {
    data class OnEmailChanged(val email: String) : SignInViewEvent
    data class OnPasswordChanged(val password: String) : SignInViewEvent
    object OnSignInClicked : SignInViewEvent
}