package com.example.chart.ui.gallery

import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LineChartViewModel : ViewModel() {

    // Backing property to avoid state update from other classes
    private  val _lineChartData = MutableStateFlow(LineData())
    // The UI collects from this StateFlow to get its state updates
    val lineChartData: StateFlow<LineData>
        get() = _lineChartData
    init {
        loadLineChartData()
    }

    private fun loadLineChartData() {
        // Adding the data
        val bearEntry = bearData()
        val dolphinsEntry = dolphinsData()
        val whalesEntry = whalesData()

        val bearDataSet = LineDataSet(bearEntry, "Bear")
        bearDataSet.valueTextColor = Color.BLUE
        bearDataSet.color = Color.BLUE
        bearDataSet.valueTextSize = 12f

        val dolphinsDataSet = LineDataSet(dolphinsEntry, "Dolphins")
        dolphinsDataSet.valueTextColor = Color.GREEN
        dolphinsDataSet.color = Color.GREEN
        dolphinsDataSet.valueTextSize = 12f

        val whalesDataSet = LineDataSet(whalesEntry, "Whales")
        whalesDataSet.valueTextColor = Color.DKGRAY
        whalesDataSet.color = Color.DKGRAY
        whalesDataSet.valueTextSize = 12f

        val totalDataSet = ArrayList<LineDataSet>()
        totalDataSet.add(bearDataSet)
        totalDataSet.add(dolphinsDataSet)
        totalDataSet.add(whalesDataSet)

        val lineData = LineData(totalDataSet as List<ILineDataSet>)
        _lineChartData.value = lineData
    }

    private fun bearData(): ArrayList<Entry>{
        val lineEntry = ArrayList<Entry>()
        lineEntry.add(Entry(1970f, 8f))
        lineEntry.add(Entry(1980f, 54f))
        lineEntry.add(Entry(1990f, 93f))
        lineEntry.add(Entry(2000f, 116f))
        lineEntry.add(Entry(2010f, 137f))
        lineEntry.add(Entry(2020f, 184f))
        return lineEntry
    }
    private fun dolphinsData(): ArrayList<Entry>{
        val lineEntry = ArrayList<Entry>()
        lineEntry.add(Entry(1970f, 150f))
        lineEntry.add(Entry(1980f, 77f))
        lineEntry.add(Entry(1990f, 32f))
        lineEntry.add(Entry(2000f, 11f))
        lineEntry.add(Entry(2010f, 6f))
        lineEntry.add(Entry(2020f, 1f))
        return lineEntry
    }
    private fun whalesData(): ArrayList<Entry>{
        val lineEntry = ArrayList<Entry>()
        lineEntry.add(Entry(1970f, 80f))
        lineEntry.add(Entry(1980f, 60f))
        lineEntry.add(Entry(1990f, 100f))
        lineEntry.add(Entry(2000f, 76f))
        lineEntry.add(Entry(2010f, 93f))
        lineEntry.add(Entry(2020f, 72f))
        return lineEntry
    }
}