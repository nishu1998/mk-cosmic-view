package com.example.project1_group15

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project1_group15.databinding.ItemNeowsBinding

class NeoWsAdapter : ListAdapter<NeoWs, NeoWsAdapter.NeoWsViewHolder>(NeoWsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeoWsViewHolder {
        val binding = ItemNeowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NeoWsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NeoWsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NeoWsViewHolder(private val binding: ItemNeowsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(neoWs: NeoWs) {
            binding.tvName.text = neoWs.name
            binding.tvMagnitude.text = "Magnitude: ${neoWs.absolute_magnitude_h}"
            binding.tvDiameter.text = "Diameter: ${neoWs.estimated_diameter.kilometers.estimated_diameter_max} km"
            binding.tvHazardous.text = if (neoWs.is_potentially_hazardous_asteroid) "Hazardous" else "Non-Hazardous"
        }
    }

    class NeoWsDiffCallback : DiffUtil.ItemCallback<NeoWs>() {
        override fun areItemsTheSame(oldItem: NeoWs, newItem: NeoWs) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: NeoWs, newItem: NeoWs) = oldItem == newItem
    }
}
