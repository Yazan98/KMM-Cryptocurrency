package com.yazantarifi.coin.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yazantarifi.android.core.ui.ApplicationColors
import com.yazantarifi.coina.models.CoinInfoItem

@Composable
fun SectionComposable(item: CoinInfoItem) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = item.title, color = ApplicationColors.getTextColor())
        Text(text = item.value, color = ApplicationColors.getTextColor())
    }
}