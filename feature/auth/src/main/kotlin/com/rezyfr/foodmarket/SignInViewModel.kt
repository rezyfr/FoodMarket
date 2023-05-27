package com.rezyfr.foodmarket

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(): ViewModel() {
    val state: StateFlow<SignInViewState> = MutableStateFlow(SignInViewState())

    data class SignInViewState(
        val email: String = "",
        val password: String = ""
    )
}