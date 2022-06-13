package com.example.chart.ui.slideshow

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chart.R
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BarChartViewModel : ViewModel() {

    private var _barChartData = MutableStateFlow(BarData())

    val barChartData: StateFlow<BarData>
        get() = _barChartData

    init {
        loadBarChartData()
    }

    private fun loadBarChartData() {
        val barEntry = ArrayList<BarEntry>()
        barEntry.add(BarEntry(0F, 3f))
        barEntry.add(BarEntry(1F, 7f))
        barEntry.add(BarEntry(2F, 8f))
        barEntry.add(BarEntry(3F, 9f))
        barEntry.add(BarEntry(4F, 11f))
        barEntry.add(BarEntry(5F, 13f))
        barEntry.add(BarEntry(6F, 13f))
        barEntry.add(BarEntry(7F, 15f))
        barEntry.add(BarEntry(8F, 16f))
        barEntry.add(BarEntry(9F, 24f))

        val barDataSet = BarDataSet(barEntry, "Average time spend on smartphones daily")
        barDataSet.color = Color.GREEN

        val barData = BarData(barDataSet)
        barData.setDrawValues(true)
        barData.setValueTextSize(15f)
        barData.setValueTextColor(Color.BLACK)
        _barChartData.value = barData
    }
}