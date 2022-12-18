package com.yazantarifi.android.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yazantarifi.android.core.BaseScreen
import com.yazantarifi.android.core.composables.ApplicationLoadingComposable
import com.yazantarifi.android.core.composables.ApplicationToolbar
import com.yazantarifi.android.core.ui.ApplicationColors
import com.yazantarifi.android.home.action.ExchangeAction
import com.yazantarifi.android.home.screens.CoinsScreenComposable
import com.yazantarifi.android.home.screens.ExchangesScreenComposable
import com.yazantarifi.android.home.viewModels.CoinsViewModel
import com.yazantarifi.android.home.viewModels.ExchangesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : BaseScreen() {

    companion object {
        fun startScreen(context: ComponentActivity) {
            context.startActivity(Intent(context, HomeScreen::class.java))
            context.finish()
        }
    }

    @Composable
    override fun OnScreenContent(savedInstanceState: Bundle?) {
        val viewModel: ExchangesViewModel = hiltViewModel()
        val coinsViewModel: CoinsViewModel = hiltViewModel()
        val navController = rememberNavController()
        var selectedItem by remember { mutableStateOf(0) }
        Scaffold(
            topBar = {
                ApplicationToolbar(
                    stringResource(id = R.string.app_name),
                    true,
                    this
                )
            },
            bottomBar = {
                NavigationBar(containerColor = ApplicationColors.getSeconderyCardsColor()) {
                    arrayListOf<Int>(0, 1)
                        .forEachIndexed { index, item ->
                            NavigationBarItem(
                                icon = {
                                    if (index == 0) {
                                        Icon(Icons.Filled.Home, contentDescription = null)
                                    } else {
                                        Icon(Icons.Filled.Add, contentDescription = null)
                                    }
                                },
                                label = {
                                        if (index == 0) {
                                            Text(text = "Exchange")
                                        } else {
                                            Text(text = "Coins")
                                        }
                                },
                                selected = selectedItem == index,
                                onClick = {
                                    if (selectedItem != index) {
                                        selectedItem = index
                                        if (selectedItem == 0) {
                                            navController.navigate("home")
                                        } else {
                                            navController.navigate("coins")
                                        }
                                    }
                                }
                            )
                        }
                }
            }
        ) {
            NavHost(navController = navController, startDestination = "home", modifier = Modifier.padding(it)) {
                composable("home") { ExchangesScreenComposable(viewModel = viewModel) }
                composable("coins") { CoinsScreenComposable(viewModel = coinsViewModel) }
            }
        }
    }

}