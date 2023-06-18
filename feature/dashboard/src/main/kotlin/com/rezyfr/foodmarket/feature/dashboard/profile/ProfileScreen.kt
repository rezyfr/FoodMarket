package com.rezyfr.foodmarket.feature.dashboard.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.component.CircleDashedBorder
import com.rezyfr.foodmarket.core.ui.component.VSpacer
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme
import com.rezyfr.foodmarket.domain.auth.model.UserDomainModel
import com.rezyfr.foodmarket.feature.dashboard.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Profile(
        viewModel = hiltViewModel(),
        modifier = modifier.fillMaxSize(),
    )
}
@Composable
private fun Profile(
    viewModel: ProfileViewModel,
    modifier: Modifier = Modifier,
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    if (viewState is ViewResult.Success) {
        ProfileContent(
            user = (viewState as ViewResult.Success<UserDomainModel>).data,
            modifier = modifier
        )
    }
}

@Composable
private fun ProfileContent(
    user: UserDomainModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ProfileHeader(user)
        Box(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.secondary.copy(alpha = 0.2f))
        )
        ProfileTab()
    }
}

@Composable
private fun ProfileHeader(
    user: UserDomainModel,
) {
    VSpacer(32)
    PhotoProfileContainer()
    VSpacer(24)
    Text(
        text = user.name,
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
    )
    Text(
        text = user.email,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.secondary,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Light
    )
    VSpacer(24)
}

@Composable
private fun PhotoProfileContainer(
    modifier: Modifier = Modifier,
    url: String = "",
) {
    Box(
        modifier = modifier
            .size(110.dp)
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .background(color = Color(0xFFF0F0F0), shape = CircleShape)
                .align(Alignment.Center)
        ) {
            AsyncImage(
                modifier = modifier
                    .background(MaterialTheme.colors.primary, shape = CircleShape),
                model = url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        CircleDashedBorder(
            color = MaterialTheme.colors.secondary,
            radius = 180f,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
@Preview(showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    FoodMarketTheme {
        ProfileContent(
            user = UserDomainModel(
                id = 1,
                name = "Rezyfr",
                email = "frotylatz@gmail.com",
                profilePhotoUrl = ""
            )
        )
    }
}