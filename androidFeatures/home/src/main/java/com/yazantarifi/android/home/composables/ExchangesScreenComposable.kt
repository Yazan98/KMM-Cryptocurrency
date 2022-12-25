package com.yazantarifi.android.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yazantarifi.android.core.composables.ApplicationLoadingComposable
import com.yazantarifi.android.core.ui.ApplicationColors
import com.yazantarifi.android.home.R
import com.yazantarifi.android.home.action.ExchangeAction
import com.yazantarifi.android.home.screens.CategoryCoinsScreen
import com.yazantarifi.android.home.viewModels.ExchangesViewModel

@Composable
fun ExchangesScreenComposable(viewModel: ExchangesViewModel) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        if (viewModel.screenContentState.value.isEmpty()) {
            viewModel.executeAction(ExchangeAction.GetExchanges)
        }
    }

    if (viewModel.screenLoadingState.value) {
        ApplicationLoadingComposable(message = stringResource(id = R.string.loading))
    } else {
        LazyColumn(modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .background(ApplicationColors.getScreenBackgroundColor())) {
            itemsIndexed(viewModel.screenContentState.value) { index, item ->
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clickable {

                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        AsyncImage(
                            model = item.image ?: "",
                            contentDescription = "",
                            modifier = Modifier.size(50.dp).clip(CircleShape))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = item.name ?: "", color = ApplicationColors.getTextColor())
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(text = item.getCountryText(), color = ApplicationColors.getSeconderyColor())
                        }
                    }

                    Text(text = item.yearEstablished?.toString() ?: "", color = ApplicationColors.getTextColor())
                }
            }
        }
    }
}