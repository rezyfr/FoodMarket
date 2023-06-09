package com.rezyfr.foodmarket.feature.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.domain.auth.model.SignInParams
import com.rezyfr.foodmarket.domain.auth.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    private var signInResult = MutableStateFlow(initialUi.result)
    private var signInParams = MutableStateFlow(initialUi.params)
    private val initialUi: SignInViewState
        get() = SignInViewState()
    private val uiFlow: Flow<SignInViewState>
        get() = combine(signInParams, signInResult) { params, result ->
            SignInViewState(
                params = params,
                result = result
            )
        }
    val uiState: StateFlow<SignInViewState> by lazy {
        uiFlow.flowOn(Dispatchers.Default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000L),
                initialValue = initialUi,
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

    fun onEvent(event: SignInViewEvent) {
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