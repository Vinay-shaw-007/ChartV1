package com.example.chart.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.chart.databinding.FragmentBarchartBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.DecimalFormat


class BarChartFragment : Fragment() {

    private var _binding: FragmentBarchartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var dailyActivities: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val barChartViewModel =
            ViewModelProvider(this)[BarChartViewModel::class.java]

        _binding = FragmentBarchartBinding.inflate(inflater, container, false)
        val root: View = binding.root
        dailyActivity()

        val xAxis = binding.barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(dailyActivities)// Formatting x index labels
        xAxis.setDrawGridLines(false)// hiding the gridlines along x axis
        xAxis.isGranularityEnabled = true// control on axis value intervals.
        xAxis.granularity = 1f// used to avoid label duplicating when zooming in.
        xAxis.labelCount = dailyActivities.size
        xAxis.labelRotationAngle = 270f
        binding.barChart.isDragEnabled = true
        binding.barChart.axisRight.isEnabled = false
        binding.barChart.description = null


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                barChartViewModel.barChartData.collect {
                    binding.barChart.data = it
                    binding.barChart.invalidate()
                    it.setValueFormatter(MyDecimalValueFormatter())
                    binding.barChart.animateY(2000, Easing.EaseInOutSine)
                }
            }
        }

        return root
    }

    private fun dailyActivity() {
        dailyActivities = ArrayList()
        dailyActivities.add("Taking photos")
        dailyActivities.add("Watching tv/films")
        dailyActivities.add("Reading books")
        dailyActivities.add("Emails")
        dailyActivities.add("Text messaging")
        dailyActivities.add("Making calls")
        dailyActivities.add("Playing games")
        dailyActivities.add("Music")
        dailyActivities.add("Social networks")
        dailyActivities.add("Internet")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class MyDecimalValueFormatter : ValueFormatter() {
        private val mFormat: DecimalFormat = DecimalFormat("#")
        override fun getFormattedValue(value: Float): String {
            return mFormat.format(value)
        }
    }
}