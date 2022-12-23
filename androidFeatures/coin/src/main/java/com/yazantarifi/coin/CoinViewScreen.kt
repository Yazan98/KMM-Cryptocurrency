package com.yazantarifi.coin

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yazantarifi.android.coin.R
import com.yazantarifi.android.core.BaseScreen
import com.yazantarifi.android.core.composables.ApplicationLoadingComposable
import com.yazantarifi.android.core.composables.ApplicationToolbar
import com.yazantarifi.android.core.navigation.CoinaNavigationsArgs
import com.yazantarifi.coin.composables.CoinPriceChartComposable
import com.yazantarifi.coin.composables.CoinTitleComposable
import com.yazantarifi.coin.viewModel.CoinAction
import com.yazantarifi.coin.viewModel.CoinViewModel
import com.yazantarifi.coina.models.CoinInfoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinViewScreen : BaseScreen() {

    @Composable
    override fun OnScreenContent(savedInstanceState: Bundle?) {
        val coinId = intent?.extras?.getString(CoinaNavigationsArgs.COIN_KEY, "") ?: ""
        val viewModel: CoinViewModel = hiltViewModel()
        LaunchedEffect(true) {
            if (viewModel.screenContentState.value == null) {
                viewModel.onNewAction(CoinAction.GetCoinByKey(coinId))
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
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(it)) {
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)) {
                        items(CoinInfoItem.getScreenItems(viewModel.screenContentState.value)) { item ->
                            when (item.type) {
                                CoinInfoItem.TYPE_TITLE -> CoinTitleComposable(item = item)
                                CoinInfoItem.TYPE_CHART -> CoinPriceChartComposable(item)
                            }
                        }
                    }
                }
            }
        }
    }

}
