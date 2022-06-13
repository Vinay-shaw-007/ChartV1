package com.example.chart.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.chart.databinding.FragmentLinechartBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LineChartFragment : Fragment() {

    private var _binding: FragmentLinechartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val lineChartViewModel =
            ViewModelProvider(this)[LineChartViewModel::class.java]

        _binding = FragmentLinechartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val xAxis = binding.lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.description = null

        // Start a coroutine in the lifecycle scope
        viewLifecycleOwner.lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancelled it when it's STOPPED.
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED and stops
                // collecting whe the lifecycle is STOPPED
                lineChartViewModel.lineChartData.collect {
                    // New value received
                    binding.lineChart.data = it
                    binding.lineChart.invalidate()
                    binding.lineChart.animateX(2000, Easing.EaseInOutQuad)
                }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}