package com.rezyfr.foodmarket.domain.order.model

data class FoodOrderModel(
    val id: String,
    val name: String,
    val price: Long,
    val quantity: Int,
    val imageUrl: String
) {
    companion object {
        fun dummy() = listOf(
            FoodOrderModel(
                id = "1",
                name = "Cherry Healthy",
                price = 348000,
                quantity = 4,
                imageUrl = "https://static.wikia.nocookie.net/gensin-impact/images/8/8f/Item_Satisfying_Salad.png/revision/latest?cb=20210417153219",
            ),
            FoodOrderModel(
                id = "2",
                name = "Burger Tamayo",
                price = 225000,
                quantity = 3,
                imageUrl = "https://i.pinimg.com/474x/63/65/a7/6365a79072a3f9c783fd739228ee1d18.jpg",
            ),
            FoodOrderModel(
                id = "3",
                name = "Sop Bumil",
                price = 42000,
                quantity = 1,
                imageUrl = "https://food.fnr.sndimg.com/content/dam/images/food/video/0/02/020/0208/0208606.jpg.rend.hgtvcom.406.305.suffix/1660150010379.jpeg",
            )
        )
    }
}
