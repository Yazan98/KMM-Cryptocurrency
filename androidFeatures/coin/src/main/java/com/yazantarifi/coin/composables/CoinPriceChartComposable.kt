package com.yazantarifi.coin.composables

import android.content.res.Resources.getSystem
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.yazantarifi.android.coin.R
import com.yazantarifi.coina.models.CoinInfoItem

@Composable
fun CoinPriceChartComposable(item: CoinInfoItem) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.px
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        val data: ArrayList<Entry> = arrayListOf()
        val maxY = item.chart?.max() ?: 0.0
        val minY = item.chart?.min() ?: 0.0
        val yAxis = maxY - minY
        item.chart?.let {
            for ((index, value) in it.withIndex()) {
                val yPosition = ((value - minY) / yAxis * 850).toFloat()
                val xPosition = (screenWidth / it.size).toFloat() * index + 1
                data.add(Entry(xPosition, yPosition))
            }
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .background(Color.White)) {
            val lineChart = LineChart(context)
            lineChart.layoutParams = ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, 850)

            val lineDataSet = LineDataSet(data, "Price Changes in Last 7 Days").apply {
                mode = LineDataSet.Mode.CUBIC_BEZIER
                color = context.getColor(R.color.purple_200)
                highLightColor = context.getColor(R.color.purple_200_height)
                fillColor = context.getColor(R.color.purple_200)
                lineWidth = 2f
                setDrawFilled(true)
                setDrawCircles(false)
            }

            AndroidView(factory = {
                lineChart.apply {
                    description.isEnabled = false
                    isDragEnabled = false
                    setTouchEnabled(false)
                    setScaleEnabled(false)
                    setDrawGridBackground(false)
                    setDrawBorders(false)
                    xAxis.isEnabled = false
                    axisLeft.setDrawAxisLine(false)
                    axisLeft.textColor = context.getColor(R.color.black)
                    axisRight.isEnabled = false
                    legend.isEnabled = true
                    animatePriceChangesGraph(lineDataSet = lineDataSet)
                }})
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}

fun LineChart.animatePriceChangesGraph(
    lineDataSet: LineDataSet? = null,
    animateXDuration: Int = 0
) {
    lineDataSet?.let {
        clear()
        data = LineData(lineDataSet).apply {
            setDrawValues(false)
        }

        if ((it.entryCount) > 20) {
            animateX(if (animateXDuration > 0) animateXDuration else 400)
        }
    }
}

val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()
