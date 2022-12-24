package com.yazantarifi.coin.composables

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.yazantarifi.coina.models.CoinInfoItem

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DescriptionComposable(item: CoinInfoItem) {
    val context = LocalContext.current
    Card(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White), shape = RoundedCornerShape(25.dp)) {
        AndroidView(factory = {
            WebView(context).apply {
                this.settings.javaScriptEnabled = true
                this.loadData(item.value, "text/html; charset=utf-8", "UTF-8")
            }
        })
    }
}
