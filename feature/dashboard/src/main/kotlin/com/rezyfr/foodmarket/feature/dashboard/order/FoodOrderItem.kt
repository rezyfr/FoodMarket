package com.rezyfr.foodmarket.feature.dashboard.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.component.HSpacer
import com.rezyfr.foodmarket.core.ui.component.VSpacer
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme
import com.rezyfr.foodmarket.core.ui.util.formatCurrency
import com.rezyfr.foodmarket.domain.order.model.FoodOrderModel

fun LazyListScope.foodOrderItem(
    openPayment: (orderId: Int) -> Unit = { },
    foodOrderResult: ViewResult<List<FoodOrderModel>> = ViewResult.Uninitialized,
) {
    when (foodOrderResult) {
        is ViewResult.Success -> {
            itemsIndexed(foodOrderResult.data) { index, item ->
                if (index == 0) VSpacer(16)
                FoodOrderItemContent(order = item, openPayment = openPayment)
                if (index == foodOrderResult.data.lastIndex) VSpacer(16)
            }
        }

        else -> Unit
    }
}
@Composable
fun FoodOrderItemContent(
    modifier: Modifier = Modifier,
    order: FoodOrderModel,
    openPayment: (orderId: Int) -> Unit = { },
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp
            )
            .clickable { openPayment(order.id.toInt()) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = order.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .background(
                    MaterialTheme.colors.secondary,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentScale = ContentScale.FillBounds
        )
        HSpacer(12)
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = order.name,
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = "${order.quantity} item(s) • ${order.price.formatCurrency()}",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun FoodExploreItemPreview() {
    FoodMarketTheme {
        FoodOrderItemContent(
            order = FoodOrderModel.dummy().first()
        )
    }
}