package com.rezyfr.foodmarket.feature.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rezyfr.foodmarket.component.FMHeaderWithBackButton
import com.rezyfr.foodmarket.component.FMTextField
import com.rezyfr.foodmarket.component.PrimaryButton
import com.rezyfr.foodmarket.component.VSpacer
import com.rezyfr.foodmarket.feature.auth.R
import com.rezyfr.foodmarket.theme.FoodMarketTheme

@Composable
fun SignUpScreen(
    onBackClicked: () -> Unit = {},
    openAddressForm: () -> Unit = {}
) {
    SignUp(
        viewModel = hiltViewModel(),
        onBackClicked = onBackClicked,
        openAddressForm = openAddressForm
    )
}
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SignUp(
    viewModel: SignUpViewModel,
    onBackClicked: () -> Unit = {},
    openAddressForm: () -> Unit = {}
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    SignUpContent(
        state = viewState,
        onContinueClicked = openAddressForm,
        onBackClicked = onBackClicked,
        onEmailChanged = { viewModel.onEvent(SignUpViewEvent.OnEmailChanged(it)) },
        onPasswordChanged = { viewModel.onEvent(SignUpViewEvent.OnPasswordChanged(it)) },
        onNameChanged = { viewModel.onEvent(SignUpViewEvent.OnNameChanged(it)) },
    )
}
@Composable
fun SignUpContent(
    state: SignUpViewState = SignUpViewState(),
    onContinueClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    onNameChanged: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        FMHeaderWithBackButton(
            onBackClicked = onBackClicked,
            headerText = stringResource(id = R.string.lbl_sign_up),
            subtitleText = stringResource(id = R.string.lbl_sign_up_motto),
        )
        Box(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.secondary.copy(alpha = 0.2f))
        )
        SignUpForm(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp),
            onContinueClicked = onContinueClicked,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            onNameChanged = onNameChanged,
            state = state
        )
    }
}
@Composable
fun SignUpForm(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit = {},
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    state: SignUpViewState
) {
    Column(modifier = modifier) {
        NameTextField(
            onNameChanged = onNameChanged,
            name = state.params.name
        )
        VSpacer(16)
        EmailTextField(
            onEmailChanged = onEmailChanged,
            email = state.params.email
        )
        VSpacer(16)
        PasswordTextField(onPasswordChanged = onPasswordChanged, password = state.params.password)
        VSpacer(24)
        ContinueButton(onContinueClicked = onContinueClicked, isValidated = state.isValidate)
    }
}
@Composable
fun NameTextField(onNameChanged: (String) -> Unit, name: String) {
    Text(stringResource(id = R.string.lbl_full_name), style = MaterialTheme.typography.body1)
    VSpacer(4)
    FMTextField(
        value = name,
        hint = stringResource(id = R.string.hint_full_name),
        onValueChange = onNameChanged
    )
}
@Composable
fun EmailTextField(onEmailChanged: (String) -> Unit, email: String) {
    Text(stringResource(id = R.string.lbl_email), style = MaterialTheme.typography.body1)
    VSpacer(4)
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
    VSpacer(4)
    FMTextField(
        value = password,
        hint = stringResource(id = R.string.hint_password),
        keyboardType = KeyboardType.Password,
        onValueChange = onPasswordChanged,
        visualTransformation = PasswordVisualTransformation(),
    )
}
@Composable
fun ContinueButton(
    onContinueClicked: () -> Unit,
    isValidated: Boolean = false,
) {
    PrimaryButton(
        Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.lbl_continue),
        onClick = onContinueClicked,
        enabled = isValidated
    )
}
@Preview(showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    FoodMarketTheme {
        SignUpContent()
    }
}