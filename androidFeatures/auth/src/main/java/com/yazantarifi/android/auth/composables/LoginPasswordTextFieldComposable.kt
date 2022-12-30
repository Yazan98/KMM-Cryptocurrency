package com.yazantarifi.android.auth.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yazantarifi.android.auth.viewModel.AuthViewModel
import com.yazantarifi.android.auth.R
import com.yazantarifi.android.core.ui.ApplicationColors

@Composable
fun LoginPasswordTextField(viewModel: AuthViewModel) {
    var text by rememberSaveable { mutableStateOf(viewModel.userPasswordState.value) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = text,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.email_pass)) },
        onValueChange = {
            text = it
            viewModel.userPasswordState.value = it
        },
        label = { Text(stringResource(id = R.string.email_pass)) },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = ApplicationColors.getApplicationColor(),
            focusedBorderColor = ApplicationColors.getApplicationColor(),
            textColor = ApplicationColors.getTextColor(),
            unfocusedLabelColor = ApplicationColors.getTextColor(),
            unfocusedBorderColor = ApplicationColors.getTextColor()
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (!passwordVisible)
                R.drawable.visibility_off
            else R.drawable.visibility

            // Please provide localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = {passwordVisible = !passwordVisible}){
                AsyncImage(model = image, modifier = Modifier.size(24.dp), contentDescription = description)
            }
        }
    )
}