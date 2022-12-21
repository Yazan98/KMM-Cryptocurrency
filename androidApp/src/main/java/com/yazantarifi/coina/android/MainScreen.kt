package com.yazantarifi.coina.android

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.yazantarifi.android.auth.LoginScreen
import com.yazantarifi.android.core.BaseScreen
import com.yazantarifi.android.home.screens.HomeScreen
import com.yazantarifi.coina.context.CoinaStorageProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class MainScreen : BaseScreen() {

    @Inject
    lateinit var storage: CoinaStorageProvider

    @Composable
    override fun OnScreenContent(savedInstanceState: Bundle?) {
        when (storage.isUserLoggedIn()) {
            true -> HomeScreen.startScreen(this)
            false -> LoginScreen.startScreen(this)
        }
    }
}
