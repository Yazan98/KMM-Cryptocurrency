package com.yazantarifi.android.home.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yazantarifi.android.core.composables.ApplicationLoadingComposable
import com.yazantarifi.android.home.R
import com.yazantarifi.android.home.action.CoinsAction
import com.yazantarifi.android.home.viewModels.CoinsViewModel

@Composable
fun CoinsScreenComposable(viewModel: CoinsViewModel) {
    if (viewModel.coinsStateListener.value.isEmpty()) {
        viewModel.onNewAction(CoinsAction.GetCoins)
    }

    if (viewModel.coinsLoadingState.value) {
        ApplicationLoadingComposable(message = stringResource(id = R.string.loading))
    } else {
        LazyColumn(modifier = Modifier.padding(10.dp).fillMaxSize()) {
            items(viewModel.coinsStateListener.value) { item ->
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = item.name ?: "")
                    Text(text = item.id ?: "")
                }
            }
        }
    }
}