package com.rezyfr.foodmarket.feature.dashboard.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rezyfr.foodmarket.core.ui.component.HSpacer
import com.rezyfr.foodmarket.core.ui.component.RatingBar
import com.rezyfr.foodmarket.core.ui.component.VSpacer
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme
import com.rezyfr.foodmarket.core.ui.util.formatCurrency
import com.rezyfr.foodmarket.domain.food.model.FoodModel

@Composable
fun FoodExploreItem(
    modifier: Modifier = Modifier,
    food: FoodModel,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(
            horizontal = 24.dp
        )
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = food.picture),
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
                text = food.name,
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = food.price.formatCurrency(),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            RatingBar(
                rating = food.rate
            )
            HSpacer(4)
            Text(
                text = "${food.rate}",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun FoodExploreItemPreview() {
    FoodMarketTheme {
        FoodExploreItem(
            food = FoodModel.getDummy().first()
        )
    }
}