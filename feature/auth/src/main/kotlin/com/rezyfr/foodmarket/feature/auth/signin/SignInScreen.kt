package com.rezyfr.foodmarket.feature.auth.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.component.FMHeader
import com.rezyfr.foodmarket.core.ui.component.FMSnackBar
import com.rezyfr.foodmarket.core.ui.component.PrimaryButton
import com.rezyfr.foodmarket.core.ui.component.SecondaryButton
import com.rezyfr.foodmarket.core.ui.component.VSpacer
import com.rezyfr.foodmarket.core.ui.component.rememberSnackBarState
import com.rezyfr.foodmarket.feature.auth.R
import com.rezyfr.foodmarket.feature.auth.component.EmailTextField
import com.rezyfr.foodmarket.feature.auth.component.PasswordTextField
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme

@Composable
fun SignInScreen(
    openSignUp: () -> Unit = {},
    openHome: () -> Unit = {}
) {
    SignIn(
        viewModel = hiltViewModel(),
        openSignUp = openSignUp,
        openHome = openHome
    )
}
@Composable
fun SignIn(
    viewModel: SignInViewModel,
    openSignUp: () -> Unit = {},
    openHome: () -> Unit = {}
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    SignInContent(
        state = viewState,
        onSignInClicked = { viewModel.onEvent(SignInViewEvent.OnSignInClicked) },
        onSignUpClicked = openSignUp,
        onEmailChanged = { viewModel.onEvent(SignInViewEvent.OnEmailChanged(it)) },
        onPasswordChanged = { viewModel.onEvent(SignInViewEvent.OnPasswordChanged(it)) },
        openHome = openHome
    )
}
@Composable
fun SignInContent(
    state: SignInViewState = SignInViewState(),
    onSignInClicked: () -> Unit = {},
    onSignUpClicked: () -> Unit = {},
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    openHome: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        FMHeader(
            headerText = stringResource(id = R.string.lbl_sign_in),
            subtitleText = stringResource(id = R.string.lbl_sign_in_motto)
        )
        Box(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.secondary.copy(alpha = 0.2f))
        )
        SignInForm(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp),
            onSignInClicked = onSignInClicked,
            onSignUpClicked = onSignUpClicked,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            state = state
        )
    }
    val snackBarState = rememberSnackBarState()
    if (state.result is ViewResult.Error) {
        LaunchedEffect(snackBarState) {
            snackBarState.addMessage(state.result.viewError.message.orEmpty())
        }
    } else if (state.result is ViewResult.Success) {
        openHome()
    }

    FMSnackBar(
        state =  snackBarState,
        containerColor = MaterialTheme.colors.error,
    )
}
@Composable
fun SignInForm(
    modifier: Modifier = Modifier,
    onSignInClicked: () -> Unit = {},
    onSignUpClicked: () -> Unit = {},
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    state: SignInViewState
) {
    Column(modifier = modifier) {
        EmailTextField(
            onEmailChanged = onEmailChanged,
            email = state.params.email
        )
        VSpacer(16)
        PasswordTextField(onPasswordChanged = onPasswordChanged, password = state.params.password)
        VSpacer(24)
        SignInButton(onSignInClicked = onSignInClicked)
        VSpacer(12)
        SignUpButton(onSignUpClicked = onSignUpClicked)
    }
}
@Composable
fun SignUpButton(
    onSignUpClicked: () -> Unit = {},
) {
    SecondaryButton(
        Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.lbl_sign_in_register),
        onClick = onSignUpClicked,
    )
}
@Composable
fun SignInButton(
    onSignInClicked: () -> Unit = {},
) {
    PrimaryButton(
        Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.lbl_sign_in),
        onClick = onSignInClicked,
    )
}
@Preview(showSystemUi = true)
@Composable
fun SignInScreenPreview() {
    FoodMarketTheme {
        SignInContent()
    }
}