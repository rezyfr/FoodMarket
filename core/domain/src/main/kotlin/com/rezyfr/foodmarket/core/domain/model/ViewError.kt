package com.rezyfr.foodmarket.core.domain.model

data class ViewError(
    override val message: String? = null,
    val code: Int? = null,
) : Throwable()
