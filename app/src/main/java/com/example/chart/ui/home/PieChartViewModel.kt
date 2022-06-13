package com.example.chart.ui.home

import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PieChartViewModel : ViewModel() {

    private var _pieChartData = MutableStateFlow(PieData())
    val pieChartData: StateFlow<PieData>
        get() = _pieChartData
    init {
        loadPieChartData()
    }

    private fun loadPieChartData() {
        val pieEntry = ArrayList<PieEntry>()
        pieEntry.add(PieEntry(0.3f, "Chrome"))
        pieEntry.add(PieEntry(0.15f, "Internet Explorer"))
        pieEntry.add(PieEntry(0.10f, "Safari"))
        pieEntry.add(PieEntry(0.25f, "Firefox"))
        pieEntry.add(PieEntry(0.2f, "Opera"))

        val colors = ArrayList<Int>()

        for (color in ColorTemplate.MATERIAL_COLORS){
            colors.add(color)
        }
        for (color in ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color)
        }

        val pieDataset = PieDataSet(pieEntry, "Browser Category")
        pieDataset.colors = colors


        val pieData = PieData(pieDataset)
        pieData.setDrawValues(true)
        pieData.setValueTextSize(30f)
        pieData.setValueTextColor(Color.BLACK)

        _pieChartData.value = pieData

    }
}