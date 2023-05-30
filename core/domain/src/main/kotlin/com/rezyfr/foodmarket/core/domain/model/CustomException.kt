package com.rezyfr.foodmarket.core.domain.model
class CustomException constructor(var code: Int = 0, override var message: String) : Exception()