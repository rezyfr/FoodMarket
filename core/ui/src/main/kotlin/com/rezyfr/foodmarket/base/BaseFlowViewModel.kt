package com.rezyfr.foodmarket.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseFlowViewModel<UiState, Event> : ViewModel() {
    private val events = MutableSharedFlow<Event>(replay = 0)
    protected abstract val initialUi: UiState
    protected abstract val uiFlow: Flow<UiState>
    protected abstract suspend fun handleEvent(event: Event)
    val uiState: StateFlow<UiState> by lazy {
        uiFlow.onEach {
            Log.d("FlowViewModel", "UI state = $it")
        }.flowOn(Dispatchers.Default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000L),
                initialValue = initialUi,
            )
    }

    init {
        viewModelScope.launch {
            events.collect(::handleEvent)
        }
        viewModelScope.launch {
            // without this delay it crashes because isn't instantiated
            delay(100)
        }
    }

    fun onEvent(event: Event) {
        viewModelScope.launch {
            events.emit(event)
        }
    }
}