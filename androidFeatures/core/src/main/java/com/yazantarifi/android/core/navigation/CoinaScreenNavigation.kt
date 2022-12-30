package com.yazantarifi.android.core.navigation

import android.content.ComponentName
import android.content.Context
import android.content.Intent

enum class CoinaScreenNavigation constructor(private val screenPath: String) {
    HOME_SCREEN("com.yazantarifi.android.home.screens.HomeScreen"),
    COIN_VIEW("com.yazantarifi.coin.CoinViewScreen");

    companion object {

        fun getIntent(context: Context, screenNavigation: CoinaScreenNavigation): Intent {
            return Intent().apply {
                component = ComponentName(context.packageName, screenNavigation.screenPath)
            }
        }
        fun startScreen(context: Context, screenNavigation: CoinaScreenNavigation) {
            Intent().apply {
                component = ComponentName(context.packageName, screenNavigation.screenPath)
                context.startActivity(this)
            }
        }
    }
}