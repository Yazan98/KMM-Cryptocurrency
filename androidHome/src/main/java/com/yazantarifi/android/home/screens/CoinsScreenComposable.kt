package com.yazantarifi.android.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yazantarifi.android.home.action.CoinsAction
import com.yazantarifi.android.home.viewModels.CoinsViewModel

@Composable
fun CoinsScreenComposable(viewModel: CoinsViewModel) {
    if (viewModel.coinsState.value.isEmpty()) {
        viewModel.onNewAction(CoinsAction.GetCoins)
    }

    LazyColumn(modifier = Modifier.padding(10.dp)) {
        items(viewModel.coinsState.value) { item ->
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                AsyncImage(
                    model = item.url ?: "",
                    contentDescription = "item Image",
                    modifier = Modifier.size(50.dp).clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(text = item.id ?: "")
            }
        }
    }
}
