package com.yazantarifi.coin.composables

import android.view.Gravity
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.yazantarifi.coina.models.CoinInfoItem

@Composable
fun CoinPriceChartComposable(item: CoinInfoItem) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        val data: ArrayList<BarEntry> = arrayListOf()
        item.chart?.let {
            for ((index, value) in it.withIndex()) {
                if (index < it.size - 1) {
                    data.add(BarEntry(value.toFloat(), it[index + 1].toFloat()))
                }
            }
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .background(Color.White)) {
            val barChart = BarChart(context)
            barChart.layoutParams = ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, 850)

            AndroidView(factory = {
                val barDataSet = BarDataSet(data, "Price Change in Last 7 Days")
                barDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
                val barData = BarData(barDataSet)
                barChart.data = barData
                val xAxis = barChart.xAxis
                xAxis.valueFormatter = IndexAxisValueFormatter(data.map { it.x.toInt().toString() })
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                barChart.setNoDataText("No data available")
                barChart.setTouchEnabled(true)
                barChart.description.isEnabled = false
                barChart.setPinchZoom(true)
                barChart.setDrawGridBackground(true)
                barChart.animateY(1000)
                barChart.legend.isEnabled = true
                barChart
            })
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}