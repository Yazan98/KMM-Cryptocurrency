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
import androidx.compose.material.icons.Icons
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yazantarifi.android.core.composables.ApplicationLoadingComposable
import com.yazantarifi.android.core.navigation.CoinaNavigationsArgs
import com.yazantarifi.android.core.navigation.CoinaScreenNavigation
import com.yazantarifi.android.core.ui.ApplicationColors
import com.yazantarifi.android.home.R
import com.yazantarifi.android.home.action.CoinsAction
import com.yazantarifi.android.home.viewModels.CoinsViewModel
import com.yazantarifi.coina.formatDecimalSeparator
import com.yazantarifi.coina.models.CoinModel
import java.util.Locale

@Composable
fun CoinsScreenComposable(viewModel: CoinsViewModel) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        if (viewModel.coinsStateListener.value.isEmpty()) {
            viewModel.executeAction(CoinsAction.GetCoins)
        }
    }

    var text by rememberSaveable { mutableStateOf("") }
    if (viewModel.coinsLoadingState.value) {
        ApplicationLoadingComposable(message = stringResource(id = R.string.loading))
    } else {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(ApplicationColors.getScreenBackgroundColor())) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = text,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = ApplicationColors.getApplicationColor(),
                    focusedBorderColor = ApplicationColors.getApplicationColor(),
                    textColor = ApplicationColors.getTextColor(),
                    unfocusedLabelColor = ApplicationColors.getTextColor(),
                    unfocusedBorderColor = ApplicationColors.getTextColor()
                ),
                placeholder = { androidx.compose.material.Text(text = stringResource(id = R.string.search_coin)) },
                onValueChange = {
                    text = it
                    viewModel.executeAction(CoinsAction.GetCoinsByQuery(it))
                },
                label = { androidx.compose.material.Text(stringResource(id = R.string.search_coin)) }
            )

            LazyColumn(modifier = Modifier
                .padding(10.dp)
                .background(ApplicationColors.getScreenBackgroundColor())) {
                itemsIndexed(viewModel.coinsStateListener.value) { index, item ->
                    CoinComposable(item) {
                        context.startActivity(CoinaScreenNavigation.getIntent(context, CoinaScreenNavigation.COIN_VIEW).apply {
                            putExtra(CoinaNavigationsArgs.COIN_KEY, item.id ?: "")
                            putExtra(CoinaNavigationsArgs.COIN_PRICE_KEY, (item.percentChange ?: 0.0) > 0.0)
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun CoinComposable(item: CoinModel, onItemClickListener: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                onItemClickListener()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            AsyncImage(
                model = item.image ?: "",
                contentDescription = "Image",
                modifier = Modifier.size(35.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = item.name ?: "", color = ApplicationColors.getTextColor())
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = item.symbol?.toUpperCase(Locale.ROOT) ?: "", color = ApplicationColors.getSeconderyColor())
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(text = ("$" + item.price?.formatDecimalSeparator()), color = ApplicationColors.getTextColor())
            Spacer(modifier = Modifier.height(3.dp))

            Row {
                val price = item.percentChange ?: 0.0
                if (price > 0.0) {
                    Icon(Icons.Filled.KeyboardArrowUp, contentDescription = null, modifier = Modifier.size(24.dp), tint = Color.Green)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = item.getPriceChangeText() + "%", color = Color.Green)
                } else {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null, modifier = Modifier.size(24.dp), tint = Color.Red)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = item.getPriceChangeText() + "%", color = Color.Red)
                }
            }
        }
    }
}