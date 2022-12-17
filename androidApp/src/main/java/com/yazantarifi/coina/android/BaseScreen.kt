package com.yazantarifi.coina.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

abstract class BaseScreen : ComponentActivity() {

    @Composable
    protected fun getApplicationColor(): Color {
        return ApplicationColors.getApplicationColor()
    }

    @Composable
    protected fun getTextColor(): Color {
        return ApplicationColors.getTextColor()
    }

    @Composable
    protected fun getWhiteColor(): Color {
        return Color.White
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {
                OnScreenContent(savedInstanceState)
            }
        }

        onScreenStarted(savedInstanceState)
    }

    @Composable
    abstract fun OnScreenContent(savedInstanceState: Bundle?)

    open fun onScreenStarted(savedInstanceState: Bundle?) = Unit

}