package com.rezyfr.foodmarket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _shouldShowSplash = MutableStateFlow(true)
    val shouldShowSplash = _shouldShowSplash.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _shouldShowSplash.value = false
        }
    }
}