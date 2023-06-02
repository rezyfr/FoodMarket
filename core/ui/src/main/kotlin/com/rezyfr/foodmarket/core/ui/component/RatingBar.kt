package com.rezyfr.foodmarket.core.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    starsColor: Color = MaterialTheme.colors.primary,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (5 - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Filled.Star, contentDescription = null, tint = starsColor,
                modifier = Modifier.size(16.dp)
            )
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Filled.StarOutline,
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier.size(16.dp)
            )
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0xFFECECEC),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
@Preview
@Composable
fun PreviewRatingBar() {
    FoodMarketTheme {
        RatingBar(rating = 2.5)
    }
}