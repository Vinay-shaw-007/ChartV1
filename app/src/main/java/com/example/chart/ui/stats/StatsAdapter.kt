package com.example.chart.ui.stats

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chart.databinding.StatsRvItemsBinding

class StatsAdapter(private val viewModel: StatsViewModel) : ListAdapter<StatsData, StatsAdapter.StatsViewHolder>(StatsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        return StatsViewHolder.from(parent, viewModel)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class StatsViewHolder private constructor(
        private val binding: StatsRvItemsBinding,
        private val viewModel: StatsViewModel,
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: StatsData) {
            binding.statsData = item
            binding.statsViewModel = viewModel
//            binding.lifecycleOwner = viewLifecycleOwner
            binding.executePendingBindings()
        }

        companion object{
            fun from(
                parent: ViewGroup,
                viewModel: StatsViewModel
            ): StatsViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = StatsRvItemsBinding.inflate(layoutInflater, parent, false)
                return StatsViewHolder(binding, viewModel)
            }
        }
    }




}
class StatsDiffCallback : DiffUtil.ItemCallback<StatsData>() {

    override fun areItemsTheSame(oldItem: StatsData, newItem: StatsData): Boolean {
        return oldItem.primaryProgress == newItem.primaryProgress || oldItem.secondaryProgress == newItem.secondaryProgress
    }


    override fun areContentsTheSame(oldItem: StatsData, newItem: StatsData): Boolean {
        return oldItem == newItem
    }


}