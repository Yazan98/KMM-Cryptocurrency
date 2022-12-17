package com.yazantarifi.coina.android


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object ApplicationColors {

    @Composable
    fun getScreenBackgroundColor(): Color {
        return MaterialTheme.colorScheme.background
    }

    @Composable
    fun getTextColor(): Color {
        return MaterialTheme.colorScheme.onBackground
    }

    @Composable
    fun getSeconderyCardsColor(): Color {
        return MaterialTheme.colorScheme.background
    }

    @Composable
    fun getPrimaryColor(): Color {
        return MaterialTheme.colorScheme.primary
    }

    @Composable
    fun getApplicationColor(): Color {
        return Color(0xFFFF5252)
    }

    @Composable
    fun getApplicationSecondColor(): Color {
        return Color(0xFFF18484)
    }

    @Composable
    fun getSeconderyColor(): Color {
        return MaterialTheme.colorScheme.primaryContainer
    }

    @Composable
    fun getOutlineColor(): Color {
        return MaterialTheme.colorScheme.outline
    }

}