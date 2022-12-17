package com.yazantarifi.android.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yazantarifi.android.core.BaseScreen
import com.yazantarifi.android.core.composables.ApplicationToolbar

class HomeScreen : BaseScreen() {

    companion object {
        fun startScreen(context: ComponentActivity) {
            context.startActivity(Intent(context, HomeScreen::class.java))
            context.finish()
        }
    }

    @Composable
    override fun OnScreenContent(savedInstanceState: Bundle?) {
        Scaffold(
            topBar = {
                ApplicationToolbar(
                    stringResource(id = R.string.app_name),
                    true,
                    this
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                Text(text = "Home")
            }
        }
    }
}