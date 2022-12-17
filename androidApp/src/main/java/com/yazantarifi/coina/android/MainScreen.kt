package com.yazantarifi.coina.android

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yazantarifi.android.core.BaseScreen
import com.yazantarifi.coina.Greeting


class MainScreen : BaseScreen() {

    @Composable
    override fun OnScreenContent(savedInstanceState: Bundle?) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Text(Greeting().greeting())
        }
    }
}
