package com.example.chart.ui.stats

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.chart.R

class StatsViewModel(context: Context, resources: Resources) : ViewModel() {

    private val _statsData = MutableLiveData<ArrayList<StatsData>>()
    val statsData: LiveData<ArrayList<StatsData>>
        get() = _statsData

    private val _navigateToStatsDetails = MutableLiveData<Boolean>()
    val navigationToStatsDetails: LiveData<Boolean>
        get() = _navigateToStatsDetails

    private val _primaryProgress = MutableLiveData<Int>()
    val primaryProgress: LiveData<Int>
        get() = _primaryProgress

    private val _secondaryProgress = MutableLiveData<Int>()
    val secondaryProgress: LiveData<Int>
        get() = _secondaryProgress

    private val _internsName = MutableLiveData<Array<String>>()
    val internsName: LiveData<Array<String>>
        get() = _internsName

    init {
        _primaryProgress.value = 10
        _secondaryProgress.value = 70
        _internsName.value = resources.getStringArray(R.array.Interns_name)
        setStatsData()
    }

    fun startNavigating(){
        _navigateToStatsDetails.value = true
    }

    fun doneNavigating(){
        _navigateToStatsDetails.value = false

    }

    private fun setStatsData() {
        val list = ArrayList<StatsData>()
        list.add(StatsData(primaryProgress = 30, secondaryProgress = 30))
        list.add(StatsData(primaryProgress = 10,  secondaryProgress = 80))
        list.add(StatsData(primaryProgress = 50,  secondaryProgress = 50))
        list.add(StatsData(primaryProgress = 20,  secondaryProgress = 40))
        list.add(StatsData(primaryProgress = 80,  secondaryProgress = 10))
        list.add(StatsData(primaryProgress = 40,  secondaryProgress = 50))
        list.add(StatsData(primaryProgress = 70,  secondaryProgress = 30))

        _statsData.value = list
    }


}
class StatsViewModelFactory(
    private val context: Context,
    private val resources: Resources,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatsViewModel::class.java)) {
            return StatsViewModel(context,resources) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}