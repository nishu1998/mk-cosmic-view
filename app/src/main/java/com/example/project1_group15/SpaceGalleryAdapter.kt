package com.example.project1_group15

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project1_group15.databinding.ItemSpaceImageBinding

class SpaceGalleryAdapter : RecyclerView.Adapter<SpaceGalleryAdapter.ImageViewHolder>() {

    private val images = mutableListOf<String>()

    inner class ImageViewHolder(
        val binding: ItemSpaceImageBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemSpaceImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(images[position])
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder_space)
            .error(R.drawable.ic_placeholder_space)
            .into(holder.binding.imgSpace)
    }

    override fun getItemCount(): Int = images.size

    fun submitList(newImages: List<String>) {
        images.clear()
        images.addAll(newImages)
        notifyDataSetChanged()
    }
}
