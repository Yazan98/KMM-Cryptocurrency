package com.yazantarifi.coina.android

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yazantarifi.android.auth.LoginScreen
import com.yazantarifi.android.core.BaseScreen
import com.yazantarifi.android.home.HomeScreen
import com.yazantarifi.coina.Greeting
import com.yazantarifi.coina.context.CoinaStorageProvider
import com.yazantarifi.coina.context.CoinaStorageProviderImplementation
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
