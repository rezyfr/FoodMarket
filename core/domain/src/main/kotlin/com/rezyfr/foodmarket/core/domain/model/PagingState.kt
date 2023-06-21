package com.rezyfr.foodmarket.core.domain.model

enum class PagingState {
    IDLE,
    LOADING,
    PAGINATING,
    ERROR,
    PAGINATION_EXHAUST,
}