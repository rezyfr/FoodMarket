package com.rezyfr.foodmarket.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.domain.auth.usecase.GetUserTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserTokenUseCase: GetUserTokenUseCase
) : ViewModel() {
    private val _tokenState: MutableStateFlow<TokenState> = MutableStateFlow(TokenState.INITIAL)
    val tokenState: StateFlow<TokenState> = _tokenState

    init {
        getUserToken()
    }

    private fun getUserToken() {
        viewModelScope.launch {
            getUserTokenUseCase.invoke(null).collectLatest {
                it.fold(
                    ifLeft = { error ->
                        Log.d("Token", error.message.toString())
                        _tokenState.value = TokenState.NOT_LOGGED
                    },
                    ifRight = { data ->
                        Log.d("Token", data)
                        _tokenState.value = if(data.isNotEmpty()) TokenState.LOGGED_VALID else TokenState.NOT_LOGGED
                    }
                )
            }
        }
    }
}

enum class TokenState {
    INITIAL,
    LOGGED_VALID,
    EXPIRED,
    NOT_LOGGED
}