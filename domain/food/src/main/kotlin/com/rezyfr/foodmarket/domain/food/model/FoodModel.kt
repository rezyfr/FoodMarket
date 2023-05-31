package com.rezyfr.foodmarket.domain.food.model

data class FoodModel(
    val name: String,
    val picture: String,
    val desc: String,
    val ingredients: List<String>,
    val price: Long,
    val rate: Long,
    val types: String?,
)
