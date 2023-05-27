package com.rezyfr.foodmarket.feature.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rezyfr.foodmarket.component.FMTextField
import com.rezyfr.foodmarket.component.PrimaryButton
import com.rezyfr.foodmarket.component.SecondaryButton
import com.rezyfr.foodmarket.component.VSpacer
import com.rezyfr.foodmarket.feature.auth.R
import com.rezyfr.foodmarket.theme.FoodMarketTheme

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
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SignIn(
    viewModel: SignInViewModel,
    openSignUp: () -> Unit = {},
    openHome: () -> Unit = {}
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    SignInContent(
        state = viewState,
        onSignInClicked = {},
        onSignUpClicked = openSignUp,
        onEmailChanged = { viewModel.onEvent(SignInViewEvent.OnEmailChanged(it)) },
        onPasswordChanged = { viewModel.onEvent(SignInViewEvent.OnPasswordChanged(it)) },
        onHomeClicked = openHome
    )
}
@Composable
fun SignInContent(
    state: SignInViewState = SignInViewState(),
    onSignInClicked: () -> Unit = {},
    onSignUpClicked: () -> Unit = {},
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    onHomeClicked: () -> Unit = {}
) {
    val scaffoldState = rememberScaffoldState()

    state.result.onFailure {
        LaunchedEffect(it.message) {
            scaffoldState.snackbarHostState.showSnackbar(it.message ?: "Unknown error")
        }
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .fillMaxWidth()
    ) {
        SignInHeader()
        VSpacer(72)
        SignInForm(
            onSignInClicked = onSignInClicked,
            onSignUpClicked = onSignUpClicked,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            state = state
        )
    }
}
@Composable
fun SignInHeader() {
    Text(
        text = stringResource(id = R.string.lbl_sign_in),
        style = MaterialTheme.typography.h6,
    )
    Text(
        text = stringResource(id = R.string.lbl_sign_in_motto),
        style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.secondary),
    )
}
@Composable
fun SignInForm(
    onSignInClicked: () -> Unit = {},
    onSignUpClicked: () -> Unit = {},
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    state: SignInViewState
) {
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
@Composable
fun EmailTextField(onEmailChanged: (String) -> Unit, email: String) {
    Text(stringResource(id = R.string.lbl_email), style = MaterialTheme.typography.body1)
    FMTextField(
        value = email,
        hint = stringResource(id = R.string.hint_email),
        keyboardType = KeyboardType.Email,
        onValueChange = onEmailChanged
    )
}
@Composable
fun PasswordTextField(onPasswordChanged: (String) -> Unit, password: String) {
    Text(stringResource(id = R.string.lbl_password), style = MaterialTheme.typography.body1)
    FMTextField(
        value = password,
        hint = stringResource(id = R.string.hint_password),
        keyboardType = KeyboardType.Password,
        onValueChange = onPasswordChanged,
        visualTransformation = PasswordVisualTransformation(),
    )
}
@Composable
fun SignUpButton(
    onSignUpClicked: () -> Unit = {},
) {
    SecondaryButton(
        Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.lbl_sign_up),
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