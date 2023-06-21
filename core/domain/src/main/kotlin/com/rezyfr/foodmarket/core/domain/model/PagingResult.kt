package com.rezyfr.foodmarket.core.domain.model

data class PagingResult<T>(
    val currentPage: Int,
    val data: List<T>,
    val firstPageUrl: String,
    val from: Int,
    val lastPage: Int,
    val lastPageUrl: String,
    val nextPageUrl: String,
)
