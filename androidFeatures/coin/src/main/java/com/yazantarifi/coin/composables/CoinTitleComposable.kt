package com.yazantarifi.coin.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yazantarifi.android.core.ui.ApplicationColors
import com.yazantarifi.coina.models.CoinInfoItem
import java.util.Locale

@Composable
fun CoinTitleComposable(item: CoinInfoItem) {
    Column {
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = item.title, color = ApplicationColors.getTextColor())
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = item.value.uppercase(Locale.ENGLISH), color = ApplicationColors.getSeconderyColor())
                Spacer(modifier = Modifier.width(5.dp))
                AsyncImage(model = item.image ?: "", contentDescription = "Coin Image", modifier = Modifier.size(24.dp))
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}
