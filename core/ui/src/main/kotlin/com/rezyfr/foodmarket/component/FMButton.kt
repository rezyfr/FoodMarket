package com.rezyfr.foodmarket.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rezyfr.foodmarket.theme.FoodMarketTheme


@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    FMButton(
        modifier = modifier,
        text = text,
        type = ButtonType.PRIMARY,
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    onClick: () -> Unit = {}
) {
    FMButton(
        modifier = modifier,
        text = text,
        type = ButtonType.SECONDARY,
        onClick = onClick
    )
}

@Composable
fun DangerButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    onClick: () -> Unit = {}
) {
    FMButton(
        modifier = modifier,
        text = text,
        type = ButtonType.DANGER,
        onClick = onClick
    )
}

@Composable
fun FMButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    type: ButtonType = ButtonType.PRIMARY,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            backgroundColor = type.backgroundColor
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 24.dp),
        enabled = enabled
    ) {
        Text(
            text = text,
            color = type.textColor,
            style = MaterialTheme.typography.button.copy(letterSpacing = 0.sp)
        )
    }
}

enum class ButtonType {
    PRIMARY, SECONDARY, DANGER;

    val textColor: Color
        @Composable
        get() = when (this) {
            PRIMARY -> MaterialTheme.colors.onPrimary
            SECONDARY -> MaterialTheme.colors.onSecondary
            DANGER -> MaterialTheme.colors.onSecondary
        }
    val backgroundColor: Color
        @Composable
        get() = when (this) {
            PRIMARY -> MaterialTheme.colors.primary
            SECONDARY -> MaterialTheme.colors.secondary
            DANGER -> MaterialTheme.colors.onError
        }
}
@Preview
@Composable
fun PrimaryButtonPreview() {
    FoodMarketTheme {
        PrimaryButton()
    }
}
@Preview
@Composable
fun SecondaryButtonPreview() {
    FoodMarketTheme {
        SecondaryButton()
    }
}
@Preview
@Composable
fun DangerButtonPreview() {
    FoodMarketTheme {
        DangerButton()
    }
}