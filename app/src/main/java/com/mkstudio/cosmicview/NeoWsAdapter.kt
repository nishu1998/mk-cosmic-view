package com.mkstudio.cosmicview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mkstudio.cosmicview.databinding.ItemNeowsBinding

class NeoWsAdapter :
    ListAdapter<NeoWs, NeoWsAdapter.NeoWsViewHolder>(NeoWsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeoWsViewHolder {
        val binding =
            ItemNeowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NeoWsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NeoWsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NeoWsViewHolder(
        private val binding: ItemNeowsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(neo: NeoWs) {
            binding.tvName.text = neo.name
            binding.tvMagnitude.text =
                "Absolute Magnitude: ${neo.absolute_magnitude_h}"

            binding.tvDiameter.text =
                "Max Diameter: ${
                    String.format(
                        "%.3f",
                        neo.estimated_diameter.kilometers.estimated_diameter_max
                    )
                } km"

            if (neo.is_potentially_hazardous_asteroid) {
                binding.tvHazardous.text = "⚠ Hazardous"
                binding.tvHazardous.setBackgroundResource(R.drawable.bg_hazard_badge)
            } else {
                binding.tvHazardous.text = "✓ Safe"
                binding.tvHazardous.setBackgroundResource(R.drawable.bg_safe_badge)
            }
        }
    }

    class NeoWsDiffCallback : DiffUtil.ItemCallback<NeoWs>() {
        override fun areItemsTheSame(old: NeoWs, new: NeoWs) = old.id == new.id
        override fun areContentsTheSame(old: NeoWs, new: NeoWs) = old == new
    }
}
