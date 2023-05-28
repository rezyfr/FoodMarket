package com.rezyfr.foodmarket.core.domain.model

sealed class ViewResult<out T> {
    object Uninitialized: ViewResult<Nothing>()
    object Loading : ViewResult<Nothing>()
    data class Success<T>(var data: T) : ViewResult<T>()
    data class Error<T>(var viewError: ViewError) : ViewResult<T>()
    data class SnackBarError<T>(var viewError: ViewError): ViewResult<T>()
}