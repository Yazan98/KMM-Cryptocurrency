package com.yazantarifi.android.home.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.yazantarifi.android.core.BaseScreen
import com.yazantarifi.android.core.composables.ApplicationLoadingComposable
import com.yazantarifi.android.core.composables.ApplicationToolbar
import com.yazantarifi.android.core.navigation.CoinaNavigationsArgs
import com.yazantarifi.android.core.navigation.CoinaScreenNavigation
import com.yazantarifi.android.core.ui.ApplicationColors
import com.yazantarifi.android.home.R
import com.yazantarifi.android.home.action.CategoriesCoinAction
import com.yazantarifi.android.home.composables.CoinComposable
import com.yazantarifi.android.home.viewModels.CategoriesCoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryCoinsScreen : BaseScreen() {

    companion object {
        private const val ARGS_CATEGORY_NAME = "args.category.name"

        fun startScreen(context: Context, categoryName: String) {
            context.startActivity(Intent(context, CategoryCoinsScreen::class.java).apply {
                putExtra(ARGS_CATEGORY_NAME, categoryName)
            })
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    override fun OnScreenContent(savedInstanceState: Bundle?) {
        val categoryName = intent?.extras?.getString(ARGS_CATEGORY_NAME, "") ?: ""
        val viewModel: CategoriesCoinViewModel = hiltViewModel()

        LaunchedEffect(true) {
            if (viewModel.screenContentState.value.isEmpty()) {
                viewModel.onNewAction(CategoriesCoinAction.GetCategoryCoin(categoryName))
            }
        }

        Scaffold(
            topBar = {
                ApplicationToolbar(
                    stringResource(id = R.string.app_name),
                    true,
                    this
                )
            },
        ) {
            if (viewModel.screenLoadingState.value) {
                ApplicationLoadingComposable(message = stringResource(id = R.string.loading))
            } else {
                LazyColumn(modifier = Modifier
                    .padding(10.dp)
                    .background(ApplicationColors.getScreenBackgroundColor())) {
                    itemsIndexed(viewModel.screenContentState.value) { index, item ->
                        CoinComposable(item) {
                            startActivity(CoinaScreenNavigation.getIntent(this@CategoryCoinsScreen, CoinaScreenNavigation.COIN_VIEW).apply {
                                putExtra(CoinaNavigationsArgs.COIN_KEY, item.id ?: "")
                            })
                        }
                    }
                }
            }
        }
    }

}
