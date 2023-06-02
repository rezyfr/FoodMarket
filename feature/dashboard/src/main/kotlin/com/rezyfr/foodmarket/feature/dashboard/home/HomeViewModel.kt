package com.rezyfr.foodmarket.feature.dashboard.home

import androidx.lifecycle.viewModelScope
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.base.BaseFlowViewModel
import com.rezyfr.foodmarket.domain.auth.model.UserDomainModel
import com.rezyfr.foodmarket.domain.auth.usecase.GetUserProfileUseCase
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.domain.food.usecase.GetAllFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getAllFoodUseCase: GetAllFoodUseCase
): BaseFlowViewModel<HomeViewState, HomeViewEvent>() {

    private var profileResult = MutableStateFlow(initialUi.profile)
    private var foodResult = MutableStateFlow(initialUi.foods)


    init {
        getProfile()
        getFoodExplore()
    }

    private fun getFoodExplore() {
        viewModelScope.launch {
            getAllFoodUseCase.invoke(Unit).collectLatest {
                it.fold(
                    { error ->
                        foodResult.value = ViewResult.Error(error)
                    },
                    { result ->
                        foodResult.value = ViewResult.Success(result)
                    }
                )
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

    override val initialUi: HomeViewState
        get() = HomeViewState()
    override val uiFlow: Flow<HomeViewState>
        get() = combine(profileResult, foodResult) { profile, food ->
            HomeViewState(
                profile = profile,
                foods = food
            )
        }
    override suspend fun handleEvent(event: HomeViewEvent) {

    }
}

data class HomeViewState(
    val profile: ViewResult<UserDomainModel> = ViewResult.Uninitialized,
    val foods: ViewResult<List<FoodModel>> = ViewResult.Uninitialized
)

sealed interface HomeViewEvent {

}