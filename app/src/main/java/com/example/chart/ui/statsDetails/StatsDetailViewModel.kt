package com.example.chart.ui.statsDetails

import android.content.res.Resources
import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chart.R
import com.example.chart.ui.stats.StatsViewModel
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class StatsDetailViewModel(resources: Resources) : ViewModel() {

    private val _internsName = MutableLiveData<Array<String>>()
    val internsName: LiveData<Array<String>>
        get() = _internsName

    private var _barChartData = MutableLiveData<BarData>()

    val barChartData: LiveData<BarData>
        get() = _barChartData

    init {
        loadBarChartData()
        _internsName.value = resources.getStringArray(R.array.Interns_name)
    }

    private fun loadBarChartData() {
        val barEntry = ArrayList<BarEntry>()
        barEntry.add(BarEntry(0F, 300F))
        barEntry.add(BarEntry(1F, 250F))
        barEntry.add(BarEntry(2F, 200F))
        barEntry.add(BarEntry(3F, 300F))
        barEntry.add(BarEntry(4F, 500F))
        barEntry.add(BarEntry(5F, 150F))
        barEntry.add(BarEntry(6F, 350F))

        val colors = ArrayList<Int>()

        for (color in ColorTemplate.MATERIAL_COLORS){
            colors.add(color)
        }
        for (color in ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color)
        }

        val barDataSet = BarDataSet(barEntry, "Daily Sales")
        barDataSet.color = Color.YELLOW

        val barData = BarData(barDataSet)
        barData.setDrawValues(true)
        barData.setValueTextSize(15f)
        barData.setValueTextColor(Color.BLACK)

        _barChartData.value = barData

    }
}
class StatsDetailViewModelFactory(
    private val resources: Resources
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatsDetailViewModel::class.java)) {
            return StatsDetailViewModel(resources) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}