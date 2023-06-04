package com.rezyfr.foodmarket.feature.order.payment.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme

@Composable
fun LabelValueItem(
    label: String = "",
    value: String = "",
    labelStyle: TextStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.secondary),
    valueStyle: TextStyle = MaterialTheme.typography.body2
) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = labelStyle)
        Text(value, style = valueStyle)
    }
}
@Preview(showBackground = true)
@Composable
fun LabelValueItemPreview() {
    FoodMarketTheme {
        LabelValueItem(
            label = "Total Price",
            value = "Rp 100.000"
        )
    }
}