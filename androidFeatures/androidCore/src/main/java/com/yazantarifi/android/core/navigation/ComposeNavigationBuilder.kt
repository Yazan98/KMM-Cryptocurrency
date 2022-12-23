package com.yazantarifi.android.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yazantarifi.coina.models.CoinaFragmentsNavigation

abstract class ComposeNavigationBuilder {

    fun initGraphConfiguration(
        navHost: NavGraphBuilder,
        onComposableLoaded: (CoinaFragmentsNavigation) -> Unit = {}
    ) {
        getSupportedComposables().apply {
            for ((key, value) in this) {
                navHost.composable(key.route) {
                    value()
                    onComposableLoaded(key)
                }
            }
        }
    }

    abstract fun getSupportedComposables(): HashMap<CoinaFragmentsNavigation, @Composable () -> Unit>

}