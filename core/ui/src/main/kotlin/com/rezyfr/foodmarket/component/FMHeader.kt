package com.rezyfr.foodmarket.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rezyfr.foodmarket.theme.FoodMarketTheme

@Composable
fun FMHeaderWithBackButton(
    headerText: String = "",
    subtitleText: String = "",
    onBackClicked: () -> Unit = {}
) {
    FMHeader(
        headerText = headerText,
        subtitleText = subtitleText,
        backButton = {
            IconButton(onClick = onBackClicked) {
                Icon(Icons.Filled.ChevronLeft, contentDescription = null, Modifier.size(48.dp))
            }
            HSpacer(24)
        }
    )
}
@Composable
fun FMHeader(
    backButton: @Composable (() -> Unit)? = null,
    trailingImage: @Composable (() -> Unit)? = null,
    headerText: String = "",
    subtitleText: String = ""
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 24.dp)
    ) {
        backButton?.invoke()
        FMHeaderContent(headerText, subtitleText)
        trailingImage?.invoke()
    }
}
@Composable
fun FMHeaderContent(
    headerText: String = "",
    subtitleText: String = ""
) {
    Column {
        Text(
            text = headerText,
            style = MaterialTheme.typography.h6,
            fontSize = 24.sp
        )
        Text(
            text = subtitleText,
            style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.secondary),
        )
    }
}
@Composable
@Preview
fun FMHeaderPreview() {
    FoodMarketTheme {
        FMHeader(
            headerText = "Header",
            subtitleText = "Subtitle",
        )
    }
}
@Composable
@Preview
fun FMHeaderWithBackPreview() {
    FoodMarketTheme {
        FMHeaderWithBackButton(
            headerText = "Header",
            subtitleText = "Subtitle",
        )
    }
}