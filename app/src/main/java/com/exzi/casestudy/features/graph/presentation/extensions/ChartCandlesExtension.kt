package com.exzi.casestudy.features.graph.presentation.extensions

import android.graphics.Color
import android.graphics.Paint
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.utils.ColorTemplate

fun CandleStickChart.chartCandlesExtension(candles: List<CandleEntry>) {
    apply {
        val dataSet = CandleDataSet(candles, "Candle Data")
        dataSet.setColors(*ColorTemplate.JOYFUL_COLORS)
        dataSet.shadowColor = Color.DKGRAY
        dataSet.shadowWidth = 0.7f
        dataSet.decreasingColor = Color.RED
        dataSet.decreasingPaintStyle = Paint.Style.FILL
        dataSet.increasingColor = Color.GREEN
        dataSet.increasingPaintStyle = Paint.Style.STROKE
        dataSet.neutralColor = Color.BLUE

        val candleData = CandleData(dataSet)

        data = candleData
        invalidate()

        val xAxis: XAxis = xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        axisLeft.setDrawGridLines(false)
        axisRight.isEnabled = false
        description.isEnabled = false
        legend.isEnabled = false
    }
}