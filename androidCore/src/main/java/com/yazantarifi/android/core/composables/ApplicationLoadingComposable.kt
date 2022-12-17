package com.yazantarifi.android.core.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ApplicationLoadingComposable(message: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = message, modifier = Modifier.padding(20.dp), textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onBackground)
    }
}