package com.rezyfr.foodmarket.feature.auth.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.rezyfr.foodmarket.core.ui.component.FMTextField
import com.rezyfr.foodmarket.feature.auth.R

@Composable
fun EmailTextField(onEmailChanged: (String) -> Unit, email: String, isError: Boolean = false) {
    Text(
        stringResource(id = R.string.lbl_email),
        style = MaterialTheme.typography.body1,
        color = if (isError) MaterialTheme.colors.error else MaterialTheme.colors.onBackground
    )
    FMTextField(
        value = email,
        hint = stringResource(id = R.string.hint_email),
        keyboardType = KeyboardType.Email,
        onValueChange = onEmailChanged,
        isError = isError
    )
}
@Composable
fun PasswordTextField(onPasswordChanged: (String) -> Unit, password: String, isError: Boolean = false) {
    Text(stringResource(id = R.string.lbl_password), style = MaterialTheme.typography.body1)
    FMTextField(
        value = password,
        hint = stringResource(id = R.string.hint_password),
        keyboardType = KeyboardType.Password,
        onValueChange = onPasswordChanged,
        visualTransformation = PasswordVisualTransformation(),
        isError = isError
    )
}