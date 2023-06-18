package com.rezyfr.foodmarket.feature.auth.signup

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rezyfr.foodmarket.core.ui.component.FMHeaderWithBackButton
import com.rezyfr.foodmarket.core.ui.component.FMSnackBar
import com.rezyfr.foodmarket.core.ui.component.FMTextField
import com.rezyfr.foodmarket.core.ui.component.PrimaryButton
import com.rezyfr.foodmarket.core.ui.component.VSpacer
import com.rezyfr.foodmarket.core.ui.component.rememberSnackBarState
import com.rezyfr.foodmarket.core.domain.model.ViewResult
import com.rezyfr.foodmarket.core.ui.component.CircleDashedBorder
import com.rezyfr.foodmarket.feature.auth.R
import com.rezyfr.foodmarket.feature.auth.component.EmailTextField
import com.rezyfr.foodmarket.feature.auth.component.PasswordTextField
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme

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
@Composable
fun SignUp(
    viewModel: SignUpViewModel,
    onBackClicked: () -> Unit = {},
    openAddressForm: () -> Unit = {}
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    SignUpContent(
        state = viewState,
        onContinueClicked = { viewModel.onEvent(SignUpViewEvent.OnSignUpClicked) } ,
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
        LazyColumn(){
            item {
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.secondary.copy(alpha = 0.2f))
                )
            }
            item {
                SignUpForm(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    onContinueClicked = onContinueClicked,
                    onEmailChanged = onEmailChanged,
                    onPasswordChanged = onPasswordChanged,
                    onNameChanged = onNameChanged,
                    state = state
                )
            }
        }
    }
    val snackBarState = rememberSnackBarState()
    if (state.result is ViewResult.Error) {
        LaunchedEffect(snackBarState) {
            snackBarState.addMessage(state.result.viewError.message.orEmpty())
        }
    } else if (state.result is ViewResult.Success) {

    }

    FMSnackBar(
        state =  snackBarState,
        containerColor = MaterialTheme.colors.error,
    )
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
        AddPhotoContainer(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        VSpacer(16)
        NameTextField(
            onNameChanged = onNameChanged,
            name = state.params.name
        )
        VSpacer(16)
        EmailTextField(
            onEmailChanged = onEmailChanged,
            email = state.params.email,
            isError = state.result is ViewResult.Error && state.result.viewError.message.orEmpty().contains("email")
        )
        VSpacer(16)
        PasswordTextField(
            onPasswordChanged = onPasswordChanged, password = state.params.password,
            isError = state.result is ViewResult.Error && state.result.viewError.message.orEmpty().contains("password")
        )
        VSpacer(24)
        ContinueButton(onContinueClicked = onContinueClicked, isValidated = state.isValidate)
    }
}
@Composable
fun AddPhotoContainer(
    modifier: Modifier = Modifier
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
            Text(
                text = stringResource(id = R.string.lbl_add_photo),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
        CircleDashedBorder(
            color = MaterialTheme.colors.secondary,
            radius = 180f,
            modifier = Modifier.align(Alignment.Center)
        )
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
//create circle dashed border with jetpack compose
