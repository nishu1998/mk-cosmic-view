package com.example.project1_group15

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project1_group15.databinding.ItemEpicImageBinding

class EpicImageAdapter : ListAdapter<EpicImageResponse, EpicImageAdapter.EpicImageViewHolder>(EpicImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpicImageViewHolder {
        val binding = ItemEpicImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpicImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpicImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EpicImageViewHolder(private val binding: ItemEpicImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: EpicImageResponse) {
            binding.tvCaption.text = image.caption
            binding.tvDate.text = image.date
            Glide.with(binding.root.context)
                .load("https://epic.gsfc.nasa.gov/archive/natural/${image.date.split(" ")[0].replace("-", "/")}/png/${image.image}.png")
                .into(binding.ivEpicImage)
        }
    }

    class EpicImageDiffCallback : DiffUtil.ItemCallback<EpicImageResponse>() {
        override fun areItemsTheSame(oldItem: EpicImageResponse, newItem: EpicImageResponse) = oldItem.identifier == newItem.identifier
        override fun areContentsTheSame(oldItem: EpicImageResponse, newItem: EpicImageResponse) = oldItem == newItem
    }
}
