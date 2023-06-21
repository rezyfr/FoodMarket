package com.rezyfr.foodmarket.feature.dashboard.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.core.domain.model.PagingState
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.domain.auth.model.UserDomainModel
import com.rezyfr.foodmarket.domain.auth.usecase.GetUserProfileUseCase
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.domain.food.usecase.GetAllFoodUseCase
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
class HomeViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getAllFoodUseCase: GetAllFoodUseCase
) : ViewModel() {
    private var profileResult = MutableStateFlow(initialUi.profile)
    private var foodResult = MutableStateFlow(initialUi.foods)
    private var page by mutableStateOf(1)
    var pagingState by mutableStateOf(PagingState.IDLE)
    var canPaginate by mutableStateOf(false)
    private val initialUi: HomeViewState
        get() = HomeViewState()
    private val uiFlow: Flow<HomeViewState>
        get() = combine(profileResult, foodResult) { profile, food ->
            HomeViewState(
                profile = profile,
                foods = food
            )
        }
    val uiState: StateFlow<HomeViewState> by lazy {
        uiFlow.flowOn(Dispatchers.Default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000L),
                initialValue = initialUi,
            )
    }

    init {
        getProfile()
        getFoodExplore()
    }

    fun getFoodExplore() {
        viewModelScope.launch {
            if (page == 1 || (page != 1 && canPaginate) && pagingState == PagingState.IDLE) {
                pagingState = if (page == 1) PagingState.LOADING else PagingState.PAGINATING

                getAllFoodUseCase.invoke(page).collectLatest {
                    it.fold(
                        ifLeft = {
                            //Handle error
                        }, ifRight = { result ->
                            if (result.data.isNotEmpty()) {
                                canPaginate = result.currentPage < result.lastPage

                                if (page == 1) foodResult.value.clear()

                                foodResult.value.addAll(result.data)
                                pagingState = PagingState.IDLE

                                if (canPaginate) page++
                            } else {
                                pagingState =
                                    if (page == 1) PagingState.ERROR else PagingState.PAGINATION_EXHAUST
                            }
                        }
                    )
                }
            }
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            getUserProfileUseCase.invoke(null).collectLatest {
                it.fold(
                    { error ->
                        profileResult.value = ViewResult.Error(error)
                    },
                    { result ->
                        profileResult.value = ViewResult.Success(result)
                    }
                )
            }
        }
    }
}

data class HomeViewState(
    val profile: ViewResult<UserDomainModel> = ViewResult.Uninitialized,
    val foods: SnapshotStateList<FoodModel> = mutableStateListOf()
)