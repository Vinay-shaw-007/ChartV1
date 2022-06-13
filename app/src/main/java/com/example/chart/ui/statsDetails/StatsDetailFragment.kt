package com.example.chart.ui.statsDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chart.R
import com.example.chart.databinding.FragmentStatsDetailBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class StatsDetailFragment : Fragment() {

    private var _binding: FragmentStatsDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: StatsDetailViewModel

    private lateinit var weekDays: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewModelFactory = StatsDetailViewModelFactory(resources)

        viewModel = ViewModelProvider(this, viewModelFactory)[StatsDetailViewModel::class.java]

        _binding = FragmentStatsDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.internsName.observe(viewLifecycleOwner){
            it ?: return@observe
            val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, it)
            binding.autoCompleteTextView.setAdapter(adapter)
        }

        val xAxis = binding.barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.valueFormatter = IndexAxisValueFormatter(setWeekDays())
        xAxis.setDrawGridLines(false)
        xAxis.setDrawLabels(true)
        xAxis.isGranularityEnabled = true
        xAxis.setDrawAxisLine(false)
        xAxis.granularity = 1f
        xAxis.labelCount = weekDays.size
        xAxis.labelRotationAngle = 270f

        binding.barChart.isDragEnabled = true
        binding.barChart.axisRight.isEnabled = false
        binding.barChart.axisLeft.isEnabled = false

        viewModel.barChartData.observe(viewLifecycleOwner) {
            it.let {
                binding.barChart.data = it
                binding.barChart.invalidate()
                binding.barChart.animateY(2000, Easing.EaseInOutSine)
            }
        }

        return root
    }

    private fun setWeekDays(): ArrayList<String> {
        weekDays = ArrayList()
        weekDays.add("Monday")
        weekDays.add("Tuesday")
        weekDays.add("Wednesday")
        weekDays.add("Thursday")
        weekDays.add("Friday")
        weekDays.add("Saturday")
        weekDays.add("Sunday")
        return weekDays
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}