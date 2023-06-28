package com.rezyfr.foodmarket.data.food.model

import com.google.gson.annotations.SerializedName
import com.rezyfr.foodmarket.domain.food.model.FoodModel

data class FoodResponse(
    @SerializedName("id")
    val id: Int?,
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
                    id = it.id ?: 0,
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
        fun FoodResponse.mapToFoodModel(): FoodModel {
            return FoodModel(
                    id = id ?: 0,
                    picture = picturePath.orEmpty(),
                    name = name.orEmpty(),
                    desc = description.orEmpty(),
                    ingredients = ingredients?.split("\r\n").orEmpty(),
                    price = price?.toLongOrNull() ?: 0L,
                    rate = rate ?: 0.0,
                    types = types.orEmpty(),
                )
        }
    }
}
