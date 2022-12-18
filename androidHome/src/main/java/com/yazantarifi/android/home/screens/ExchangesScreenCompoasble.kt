package com.yazantarifi.android.home.screens

import androidx.compose.foundation.layout.Column
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
import com.yazantarifi.android.home.action.ExchangeAction
import com.yazantarifi.android.home.viewModels.ExchangesViewModel

@Composable
fun ExchangesScreenComposable(viewModel: ExchangesViewModel) {
    if (viewModel.exchangesStateListener.value.isEmpty()) {
        viewModel.onNewAction(ExchangeAction.GetExchanges)
    }

    if (viewModel.exchangesLoadingState.value) {
        ApplicationLoadingComposable(message = stringResource(id = R.string.loading))
    } else {
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(viewModel.exchangesStateListener.value) { item ->
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = item.name ?: "")
                    Text(text = item.website ?: "")
                }
            }
        }
    }
}