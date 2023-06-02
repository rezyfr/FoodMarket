package com.rezyfr.foodmarket.feature.order.food

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.component.FMButton
import com.rezyfr.foodmarket.core.ui.component.HSpacer
import com.rezyfr.foodmarket.core.ui.component.PrimaryButton
import com.rezyfr.foodmarket.core.ui.component.RatingBar
import com.rezyfr.foodmarket.core.ui.component.VSpacer
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme
import com.rezyfr.foodmarket.core.ui.util.formatCurrency
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.feature.order.R

@Composable
fun FoodDetailScreen(
    navigateUp: () -> Unit = {},
    openPayment: () -> Unit = {}
) {
    FoodDetail(
        viewModel = hiltViewModel(),
        navigateUp = navigateUp,
        openPayment = openPayment
    )
}
@Composable
fun FoodDetail(
    viewModel: FoodDetailViewModel,
    navigateUp: () -> Unit,
    openPayment: () -> Unit
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    FoodDetailContent(
        state = viewState,
        navigateUp = navigateUp,
        changeQty = { viewModel.onEvent(FoodDetailEvent.ChangeQty(it)) },
        openPayment = openPayment
    )
}
@Composable
fun FoodDetailContent(
    state: FoodDetailViewState,
    navigateUp: () -> Unit = {},
    changeQty: (Boolean) -> Unit = {},
    openPayment: () -> Unit = {},
) {
    BoxWithConstraints(Modifier.fillMaxSize()) {
        if (state.food is ViewResult.Success) {
            FoodDetailHeader(
                Modifier
                    .align(Alignment.TopCenter)
                    .height(maxHeight / 2),
                navigateUp = navigateUp,
                state.food.data
            )
            FoodDetailBody(
                Modifier.align(Alignment.BottomCenter),
                changeQty,
                openPayment,
                state.food.data,
                state.orderParams
            )
        }
    }
}
@Composable
fun FoodDetailBody(
    modifier: Modifier = Modifier,
    changeQty: (Boolean) -> Unit = {},
    openPayment: () -> Unit = {},
    data: FoodModel,
    orderParams: OrderParams
) {
    Column(modifier.background(Color.White, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FoodNameSection(food = data)
            Spacer(modifier = Modifier.weight(1f))
            FoodQtySection(
                changeQty = changeQty,
                orderParams = orderParams,
            )
        }
        DescriptionSection(
            modifier = Modifier.padding(horizontal = 16.dp),
            desc = data.desc
        )
        VSpacer(16)
        IngredientsSection(
            modifier = Modifier.padding(horizontal = 16.dp),
            ingredients = data.ingredients
        )
        VSpacer(24)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            PriceSection(
                modifier = Modifier,
                price = orderParams.total.toLong(),
            )
            OrderButton(onClick = { openPayment() })
        }
    }
}
@Composable
fun OrderButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    PrimaryButton(
        modifier = modifier,
        text = stringResource(id = R.string.lbl_order_now),
        onClick = onClick
    )
}
@Composable
fun PriceSection(modifier: Modifier, price: Long) {
    Column {
        Text(
            text = stringResource(id = R.string.lbl_total_price),
            style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.secondary),
            modifier = modifier
        )
        Text(
            text = price.formatCurrency(),
            style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
            modifier = modifier
        )
    }
}
@Composable
fun IngredientsSection(
    modifier: Modifier = Modifier,
    ingredients: List<String> = listOf()
) {
    Text(
        text = stringResource(id = R.string.lbl_ingredients),
        style = MaterialTheme.typography.body2,
        modifier = modifier
    )
    VSpacer(4)
    Text(
        text = ingredients.joinToString(", "),
        style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.secondary),
        modifier = modifier
    )
}
@Composable
fun DescriptionSection(
    modifier: Modifier = Modifier,
    desc: String = ""
) {
    Text(
        text = desc,
        style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.secondary),
        modifier = modifier,
        lineHeight = 26.sp
    )
}
@Composable
fun FoodQtySection(
    modifier: Modifier = Modifier,
    changeQty: (Boolean) -> Unit,
    orderParams: OrderParams
) {
    Box(
        modifier
            .border(1.dp, MaterialTheme.colors.onSurface, RoundedCornerShape(8.dp))
            .clickable { changeQty.invoke(false) }
            .size(24.dp)
    ) {
        Text(
            text = "-",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.Center)
        )
    }
    HSpacer(8)
    Text(
        modifier = Modifier.width(24.dp),
        text = "${orderParams.quantity}",
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center
    )
    HSpacer(8)
    Box(
        Modifier
            .border(1.dp, MaterialTheme.colors.onSurface, RoundedCornerShape(8.dp))
            .clickable { changeQty.invoke(true) }
            .size(24.dp)
    ) {
        Text(
            text = "+",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
@Composable
fun FoodNameSection(modifier: Modifier = Modifier, food: FoodModel) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = food.name,
            style = MaterialTheme.typography.body1,
        )
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
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
@Composable
fun FoodDetailHeader(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    food: FoodModel,
) {
    Image(
        painter = rememberAsyncImagePainter(model = food.picture),
        contentDescription = null,
        modifier = modifier
            .background(MaterialTheme.colors.secondary)
            .fillMaxWidth(),
        contentScale = ContentScale.FillBounds
    )
    IconButton(onClick = navigateUp) {
        Icon(
            Icons.Filled.ChevronLeft,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.padding(24.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewQtySection() {
    FoodMarketTheme {
        Row {
            FoodQtySection(changeQty = {}, orderParams = OrderParams("1", 1, 10000.0))
        }
    }
}
@Preview
@Composable
fun PreviewFoodDetail() {
    FoodMarketTheme {
        FoodDetailContent(
            state = FoodDetailViewState(
                food = ViewResult.Success(FoodModel.getDummy().first().copy(picture = ""))
            )
        ) {
        }
    }
}
