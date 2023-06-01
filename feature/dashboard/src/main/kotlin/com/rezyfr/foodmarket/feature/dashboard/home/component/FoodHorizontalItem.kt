package com.rezyfr.foodmarket.feature.dashboard.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.rezyfr.foodmarket.domain.food.model.FoodModel

@Composable
fun FoodHorizontalItem(
    modifier: Modifier = Modifier,
    food: FoodModel,
) {
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = food.picture),
                contentDescription = null,
                modifier = Modifier
                    .background(
                        MaterialTheme.colors.secondary,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
                    .width(200.dp)
                    .height(140.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = food.name,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(
                    top = 12.dp,
                    start = 12.dp,
                    end = 12.dp
                )
            )
            VSpacer(6)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    bottom = 12.dp,
                    start = 12.dp,
                    end = 12.dp
                )
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
}
@Preview
@Composable
fun SecondaryButtonPreview() {
    FoodMarketTheme {
        FoodHorizontalItem(
            food = FoodModel.getDummy().first()
        )
    }
}