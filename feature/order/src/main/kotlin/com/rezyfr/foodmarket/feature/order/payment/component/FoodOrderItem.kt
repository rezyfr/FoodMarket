package com.rezyfr.foodmarket.feature.order.payment.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rezyfr.foodmarket.core.ui.component.HSpacer
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme
import com.rezyfr.foodmarket.core.ui.util.formatCurrency
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.domain.order.model.PaymentParams

@Composable
internal fun FoodOrderItem(
    modifier: Modifier = Modifier,
    params: PaymentParams,
    name: String,
    image: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = image),
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
                text = name,
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = params.foodPrice.formatCurrency(),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary
            )
        }
        Text(
            text = "${params.quantity} items",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun FoodExploreItemPreview() {
    FoodMarketTheme {
        FoodOrderItem(
            name = "Food 1",
            image = "",
            params = PaymentParams.empty()
        )
    }
}