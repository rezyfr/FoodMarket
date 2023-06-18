package com.rezyfr.foodmarket.feature.dashboard.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.domain.auth.model.UserDomainModel
import com.rezyfr.foodmarket.domain.auth.usecase.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<ViewResult<UserDomainModel>> = MutableStateFlow(ViewResult.Uninitialized)
    val uiState: StateFlow<ViewResult<UserDomainModel>> = _uiState

    init {
        getUserProfile()
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            getUserProfileUseCase.invoke(null).collectLatest {
                it.fold(
                    ifLeft = { error ->
                        _uiState.update { ViewResult.Error(error) }
                    },
                    ifRight = { data ->
                        _uiState.update { ViewResult.Success(data) }
                    }
                )
            }
        }
    }
}