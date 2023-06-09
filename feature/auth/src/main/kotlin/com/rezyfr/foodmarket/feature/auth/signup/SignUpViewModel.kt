package com.rezyfr.foodmarket.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams
import com.rezyfr.foodmarket.domain.auth.usecase.SignUpUseCase
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
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private var signUpParams = MutableStateFlow(initialUi.params)
    private var signUpResult = MutableStateFlow(initialUi.result)
    private val initialUi: SignUpViewState
        get() = SignUpViewState()
    private val uiFlow: Flow<SignUpViewState>
        get() = combine(signUpParams, signUpResult) { params, result ->
            SignUpViewState(
                params = params,
                isValidate = params.email.isNotEmpty() && params.password.isNotEmpty() && params.name.isNotEmpty(),
                result = result
            )
        }
    val uiState: StateFlow<SignUpViewState> by lazy {
        uiFlow.flowOn(Dispatchers.Default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000L),
                initialValue = initialUi,
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
                    { error -> signUpResult.value = ViewResult.Error(error) },
                    { result -> signUpResult.value = ViewResult.Success(result) }
                )
            }
        }
    }

    fun onEvent(event: SignUpViewEvent) {
        when (event) {
            is SignUpViewEvent.OnEmailChanged -> onEmailChanged(event.email)
            is SignUpViewEvent.OnPasswordChanged -> onPasswordChanged(event.password)
            is SignUpViewEvent.OnNameChanged -> onNameChanged(event.name)
            SignUpViewEvent.OnSignUpClicked -> onSignUpClicked()
        }
    }
}