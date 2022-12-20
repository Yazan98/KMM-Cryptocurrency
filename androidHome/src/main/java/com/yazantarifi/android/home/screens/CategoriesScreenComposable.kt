package com.yazantarifi.android.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yazantarifi.android.core.composables.ApplicationLoadingComposable
import com.yazantarifi.android.core.ui.ApplicationColors
import com.yazantarifi.android.home.R
import com.yazantarifi.android.home.action.CategoriesAction
import com.yazantarifi.android.home.action.CoinsAction
import com.yazantarifi.android.home.viewModels.CategoriesViewModel
import com.yazantarifi.coina.formatDecimalSeparator
import java.util.Locale

@Composable
fun CategoriesScreenComposable(viewModel: CategoriesViewModel) {
    LaunchedEffect(true) {
        if (viewModel.screenContentState.value.isEmpty()) {
            viewModel.onNewAction(CategoriesAction.GetCategories)
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
                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "$index", color = ApplicationColors.getTextColor(), modifier = Modifier.width(20.dp))
                    Spacer(modifier = Modifier.width(20.dp))

                    Row {
                        item.topCoins?.forEach {
                            AsyncImage(model = it, contentDescription = "Image", modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(2.dp))
                        }
                    }

                    Spacer(modifier = Modifier.width(30.dp))
                    Text(text = item.name ?: "", color = ApplicationColors.getTextColor())
                }
            }
        }
    }
}