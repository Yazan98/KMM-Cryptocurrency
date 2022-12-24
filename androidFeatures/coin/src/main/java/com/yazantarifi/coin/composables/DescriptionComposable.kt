package com.yazantarifi.coin.composables

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.yazantarifi.coina.models.CoinInfoItem

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DescriptionComposable(item: CoinInfoItem) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxWidth()) {
        AndroidView(factory = {
            WebView(context).apply {
                this.settings.javaScriptEnabled = true
                this.loadData(item.value, "text/html; charset=utf-8", "UTF-8")
            }
        })
    }
}
