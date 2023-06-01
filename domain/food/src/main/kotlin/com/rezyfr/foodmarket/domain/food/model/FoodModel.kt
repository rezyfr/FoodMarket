package com.rezyfr.foodmarket.domain.food.model

data class FoodModel(
    val name: String,
    val picture: String,
    val desc: String,
    val ingredients: List<String>,
    val price: Long,
    val rate: Double,
    val types: String?,
) {
    companion object {
        fun getDummy() = listOf<FoodModel>(
            FoodModel(
                name = "Cherry Healthy",
                picture = "https://static.wikia.nocookie.net/gensin-impact/images/8/8f/Item_Satisfying_Salad.png/revision/latest?cb=20210417153219",
                desc = "Makanan khas Bandung yang cukup sering dipesan oleh anak muda dengan pola makan yang cukup tinggi dengan mengutamakan diet yang sehat dan teratur.",
                ingredients = listOf("Seledri", "telur", "blueberry", "madu"),
                price = 87000,
                rate = 4.5,
                types = "",
            ),
            FoodModel(
                name = "Burger Tamayo",
                picture = "https://i.pinimg.com/474x/63/65/a7/6365a79072a3f9c783fd739228ee1d18.jpg",
                desc = "Burger tamayo adalah burger yang dibuat dari daging sapi pilihan dengan keju yang meleleh",
                ingredients = listOf("Seledri", "telur", "daging", "roti"),
                price = 75000,
                rate = 4.6,
                types = "",
            ),
            FoodModel(
                name = "Sop Bumil",
                picture = "https://food.fnr.sndimg.com/content/dam/images/food/video/0/02/020/0208/0208606.jpg.rend.hgtvcom.406.305.suffix/1660150010379.jpeg",
                desc = "Sop bumil adalah sup yang dibuat dari bahan bahan alami yang sehat tanpa bahan pengawet",
                ingredients = listOf("Tomat", "seledri", "wortel", "daging"),
                price = 42000,
                rate = 4.9,
                types = "",
            ),
        )
    }
}
