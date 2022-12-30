package com.yazantarifi.android.auth.composables

import androidx.compose.foundation.layout.fillMaxWidth
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
import com.yazantarifi.android.auth.viewModel.AuthViewModel
import com.yazantarifi.android.auth.R
import com.yazantarifi.android.core.ui.ApplicationColors

@Composable
fun LoginEmailTextField(viewModel: AuthViewModel) {
    var text by rememberSaveable { mutableStateOf(viewModel.userEmailState.value) }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = ApplicationColors.getApplicationColor(),
            focusedBorderColor = ApplicationColors.getApplicationColor(),
            textColor = ApplicationColors.getTextColor(),
            unfocusedLabelColor = ApplicationColors.getTextColor(),
            unfocusedBorderColor = ApplicationColors.getTextColor()
        ),
        placeholder = { Text(text = stringResource(id = R.string.email_hint)) },
        onValueChange = {
            text = it
            viewModel.userEmailState.value = it
        },
        label = { Text(stringResource(id = R.string.email_hint)) }
    )
}