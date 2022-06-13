package com.example.chart.ui.stats

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chart.R
import com.example.chart.databinding.FragmentStatsBinding

class StatsFragment : Fragment() {


    private var _binding: FragmentStatsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: StatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val viewModelFactory = StatsViewModelFactory(requireContext(), resources)

        viewModel = ViewModelProvider(this, viewModelFactory)[StatsViewModel::class.java]

        viewModel.primaryProgress.observe(viewLifecycleOwner){
            binding.totalCapacityLayout.stackedHorizontalProgressBar.progress = it
        }

        viewModel.secondaryProgress.observe(viewLifecycleOwner){
            binding.totalCapacityLayout.stackedHorizontalProgressBar.secondaryProgress = it
        }

        binding.autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, l ->
                Toast.makeText(requireContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
            }

        viewModel.internsName.observe(viewLifecycleOwner){
            val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, it)
            binding.autoCompleteTextView.setAdapter(adapter)
        }

        val adapter = StatsAdapter(viewModel)
        binding.rvStats.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStats.adapter = adapter

        viewModel.statsData.observe(viewLifecycleOwner){
            it?.let {
                adapter.submitList(it)
            }
        }

        viewModel.navigationToStatsDetails.observe(viewLifecycleOwner){
            it ?: return@observe
            if (it){
                Log.d("StatsFragments" , it.toString())
                this.findNavController().navigate(
                    StatsFragmentDirections.actionNavProgressToStatsDetailFragment()
                )
                viewModel.doneNavigating()
            }
        }

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

