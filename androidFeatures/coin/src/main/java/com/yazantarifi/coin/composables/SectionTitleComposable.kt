package com.yazantarifi.coin.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yazantarifi.android.core.ui.ApplicationColors
import com.yazantarifi.coina.models.CoinInfoItem

@Composable
fun SectionTitleComposable(item: CoinInfoItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = item.title, color = ApplicationColors.getTextColor(), fontWeight = FontWeight.Bold)
    }
}