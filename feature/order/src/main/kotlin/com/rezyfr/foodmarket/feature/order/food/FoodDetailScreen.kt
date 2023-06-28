package com.rezyfr.foodmarket.feature.order.food

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.rezyfr.foodmarket.core.ui.component.FMHeaderWithTrailingContentAndBackButton
import com.rezyfr.foodmarket.core.ui.component.HSpacer
import com.rezyfr.foodmarket.core.ui.component.PrimaryButton
import com.rezyfr.foodmarket.core.ui.component.RatingBar
import com.rezyfr.foodmarket.core.ui.component.VSpacer
import com.rezyfr.foodmarket.core.ui.theme.FMTheme
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme
import com.rezyfr.foodmarket.core.ui.util.formatCurrency
import com.rezyfr.foodmarket.domain.food.model.FoodModel
import com.rezyfr.foodmarket.domain.order.model.PaymentParams
import com.rezyfr.foodmarket.feature.order.R
import com.rezyfr.foodmarket.feature.order.model.OrderParams
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FoodDetailScreen(
    navigateUp: () -> Unit = {},
    openPayment: (PaymentParams, String, String) -> Unit = { _, _, _ -> }
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
    openPayment: (PaymentParams, String, String) -> Unit = { _, _, _ -> }
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) {
        viewModel.effect.collectLatest {
            when (it) {
                is FoodDetailViewEffect.OpenPaymentScreen -> openPayment(
                    it.data,
                    it.foodName,
                    it.foodImage
                )
            }
        }
    }
    FoodDetailContent(
        state = viewState,
        navigateUp = navigateUp,
        changeQty = { viewModel.onEvent(FoodDetailEvent.ChangeQty(it)) },
        openPayment = { viewModel.onEvent(FoodDetailEvent.OnOrderClicked) }
    )
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FoodDetailContent(
    state: FoodDetailViewState,
    navigateUp: () -> Unit = {},
    changeQty: (Boolean) -> Unit = {},
    openPayment: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            FoodDetailHeader(
                navigateUp = navigateUp,
                "KFC",
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            if (state.food is ViewResult.Success) {
                FoodDetailBody(
                    Modifier,
                    changeQty,
                    openPayment,
                    state.food.data,
                    state.orderParams
                )
            }
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
        Image(
            painter = rememberAsyncImagePainter(model = data.picture),
            contentDescription = null,
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colors.secondary),
            contentScale = ContentScale.FillBounds
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FoodNameSection(food = data)
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colors.surface)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.FavoriteBorder,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
        DescriptionSection(
            modifier = Modifier.padding(horizontal = 16.dp),
            desc = data.desc
        )
        VSpacer(24)
        IngredientsSection(
            modifier = Modifier.padding(horizontal = 16.dp),
            ingredients = data.ingredients
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PriceSection(
                modifier = Modifier.weight(1f),
                price = data.price,
            )
            OrderButton(
                onClick = { openPayment() },
                modifier = Modifier.weight(1f)
            )
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
        onClick = onClick,
    )
}
@Composable
fun PriceSection(modifier: Modifier, price: Long) {
    Text(
        text = price.formatCurrency(),
        style = FMTheme.typography.title.extraLarge,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}
@Composable
fun IngredientsSection(
    modifier: Modifier = Modifier,
    ingredients: List<String> = listOf()
) {
    Text(
        text = stringResource(id = R.string.lbl_ingredients),
        style = FMTheme.typography.title.large,
        modifier = modifier
    )
    VSpacer(4)
    Text(
        text = ingredients.joinToString(", "),
        style = FMTheme.typography.title.tiny.demi.copy(
            color = MaterialTheme.colors.onSurface
        ),
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
        style = FMTheme.typography.body.moderate.regular,
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
            style = FMTheme.typography.title.extraLarge,
        )
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(16.dp)
            )
            HSpacer(4)
            Text(
                text = "${food.rate}",
                style = FMTheme.typography.title.tiny.bold,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}
@Composable
fun FoodDetailHeader(
    navigateUp: () -> Unit,
    foodName: String,
) {
    FMHeaderWithTrailingContentAndBackButton(
        headerText = foodName,
        trailingContent = {
            Icon(imageVector = Icons.Outlined.MoreHoriz, contentDescription = null)
        },
        onBackClicked = navigateUp
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewQtySection() {
    FoodMarketTheme {
        Row {
            FoodQtySection(changeQty = {}, orderParams = OrderParams(1, 1, 10000.0))
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
