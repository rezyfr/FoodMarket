package com.rezyfr.foodmarket.core.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rezyfr.foodmarket.core.ui.theme.FoodMarketTheme

@Composable
fun FMTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue = TextFieldValue(),
    hint: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (TextFieldValue) -> Unit = {},
) {
    var focusState by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange.invoke(it)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.onSurface,
            focusedBorderColor = MaterialTheme.colors.onSurface,
            unfocusedBorderColor = MaterialTheme.colors.onSurface,
        ),
        modifier = modifier
            .onFocusChanged { focusState = it.isFocused }
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        textStyle = MaterialTheme.typography.body2,
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                text = if (focusState) "" else hint, style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.secondary
                )
            )
        }
    )
}
@Composable
fun FMTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    hint: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    onValueChange: (String) -> Unit = {},
) {
    var focusState by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange.invoke(it)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.onSurface,
            focusedBorderColor = MaterialTheme.colors.secondaryVariant,
            unfocusedBorderColor = MaterialTheme.colors.onSurface,
            cursorColor = MaterialTheme.colors.onSurface,
        ),
        isError = isError,
        modifier = modifier
            .onFocusChanged { focusState = it.isFocused }
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        textStyle = MaterialTheme.typography.body2,
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                text = if (focusState) "" else hint, style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.secondary
                )
            )
        },
    )
}
@Preview
@Composable
fun FMTextFieldPreview() {
    FoodMarketTheme {
        Surface {
            FMTextField(
                hint = "Email",
                value = TextFieldValue("frotylatz@gmail.com"),
            )
        }
    }
}