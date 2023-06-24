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
    val ingredients: String?,
    @SerializedName("price")
    val price: String?,
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
                    ingredients = it.ingredients?.split("\\r\\n").orEmpty(),
                    price = it.price?.toLongOrNull() ?: 0L,
                    rate = it.rate ?: 0.0,
                    types = it.types.orEmpty(),
                )
            }
        }
    }
}
