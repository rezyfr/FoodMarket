package com.rezyfr.foodmarket.data.food.model

import com.google.gson.annotations.SerializedName
import com.rezyfr.foodmarket.domain.food.model.FoodModel

data class FoodResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("picturePath")
    val picturePath: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("ingredients")
    val ingredients: List<String>?,
    @SerializedName("price")
    val price: Long?,
    @SerializedName("rate")
    val rate: Double?,
    @SerializedName("types")
    val types: String?,
) {
    companion object {
        fun List<FoodResponse>.mapToFoodModel(): List<FoodModel> {
            return this.map {
                FoodModel(
                    id = it.id.orEmpty(),
                    picture = it.picturePath.orEmpty(),
                    name = it.name.orEmpty(),
                    desc = it.description.orEmpty(),
                    ingredients = it.ingredients.orEmpty(),
                    price = it.price ?: 0,
                    rate = it.rate ?: 0.0,
                    types = it.types.orEmpty(),
                )
            }
        }
    }
}
