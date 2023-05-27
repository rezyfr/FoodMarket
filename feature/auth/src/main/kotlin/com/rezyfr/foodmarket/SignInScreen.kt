package com.rezyfr.foodmarket

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rezyfr.foodmarket.component.ButtonType
import com.rezyfr.foodmarket.component.FMButton
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
@Composable
fun SignIn(
    viewModel: SignInViewModel,
    openSignUp: () -> Unit = {},
    openHome: () -> Unit = {}
) {
    val viewState by viewModel.state.collectAsState()

    SignInContent(
        state = viewState,
        onSignInClicked = {},
        onSignUpClicked = openSignUp,
        onEmailChanged = {},
        onPasswordChanged = {},
        onHomeClicked = openHome
    )
}
@Composable
fun SignInContent(
    state: SignInViewModel.SignInViewState = SignInViewModel.SignInViewState(),
    onSignInClicked: () -> Unit = {},
    onSignUpClicked: () -> Unit = {},
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    onHomeClicked: () -> Unit = {}
) {
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
    state: SignInViewModel.SignInViewState
) {
    EmailTextField(
        onEmailChanged = onEmailChanged,
        email = state.email
    )
    VSpacer(16)
    PasswordTextField(onPasswordChanged = onPasswordChanged, password = state.password)
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
    ) {
        onEmailChanged(it)
    }
}
@Composable
fun PasswordTextField(onPasswordChanged: (String) -> Unit, password: String) {
    Text(stringResource(id = R.string.lbl_password), style = MaterialTheme.typography.body1)
    FMTextField(
        value = password,
        hint = stringResource(id = R.string.hint_password),
        keyboardType = KeyboardType.Password,
    ) {
        onPasswordChanged(it)
    }
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