package com.yazantarifi.android.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.yazantarifi.android.core.BaseScreen

class LoginScreen : BaseScreen() {

    companion object {
        fun startScreen(context: ComponentActivity) {
            context.startActivity(Intent(context, LoginScreen::class.java))
            context.finish()
        }
    }

    @Composable
    override fun OnScreenContent(savedInstanceState: Bundle?) {
        Text(text = "Login")
    }

}

