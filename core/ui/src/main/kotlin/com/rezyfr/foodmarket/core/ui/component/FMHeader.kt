package com.rezyfr.foodmarket.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme

@Composable
fun FMHeaderWithBackButton(
    headerText: String = "",
    onBackClicked: () -> Unit = {}
) {
    FMHeader(
        headerText = headerText,
        backButton = {
            IconButton(onClick = onBackClicked) {
                Icon(Icons.Rounded.ChevronLeft, contentDescription = null, Modifier.size(48.dp))
            }
            HSpacer(24)
        }
    )
}
@Composable
fun FMHeaderWithTrailingContent(
    headerText: String = "",
    trailingContent: @Composable (() -> Unit)
) {
    FMHeader(
        headerText = headerText,
        trailingImage = {
            trailingContent.invoke()
        }
    )
}
@Composable
fun FMHeaderWithTrailingContentAndBackButton(
    headerText: String = "",
    trailingContent: @Composable (() -> Unit),
    onBackClicked: () -> Unit = {}
) {
    FMHeader(
        headerText = headerText,
        trailingImage = {
            trailingContent.invoke()
        },
        backButton = {
            IconButton(onClick = onBackClicked) {
                Icon(Icons.Rounded.ChevronLeft, contentDescription = null, Modifier.size(48.dp))
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
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        backButton?.invoke()
        trailingImage?.let {
            Spacer(modifier = Modifier.weight(1f))
            trailingImage.invoke()
        }
    }
    FMHeaderContent(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp), headerText)
}
@Composable
fun FMHeaderContent(
    modifier: Modifier = Modifier,
    headerText: String = "",
) {
    Text(
        text = headerText,
        style = MaterialTheme.typography.h6,
        fontSize = 24.sp,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
@Preview
fun FMHeaderWithBackPreview() {
    FoodMarketTheme {
        FMHeaderWithBackButton(
            headerText = "Header",
        )
    }
}
@Composable
@Preview
fun FMHeaderWithTrailingImagePreview() {
    FoodMarketTheme {
        FMHeaderWithTrailingContent(
            headerText = "Header",
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(MaterialTheme.colors.primary, RoundedCornerShape(8.dp)),
            )
        }
    }
}
@Composable
@Preview
fun FMHeaderWithTrailingContentAndBackButtonPreview() {
    FoodMarketTheme {
        FMHeaderWithTrailingContentAndBackButton(
            headerText = "Header",
            trailingContent = {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(MaterialTheme.colors.primary, RoundedCornerShape(8.dp)),
                )
            },
            onBackClicked = {}
        )
    }
}
