package com.example.project1_group15

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project1_group15.databinding.ItemSpaceImageBinding

class SpaceGalleryAdapter(
    private val images: MutableList<String> = mutableListOf(),
    private val onImageClick: (String) -> Unit
) : RecyclerView.Adapter<SpaceGalleryAdapter.ImageViewHolder>() {

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
        val imageUrl = images[position]

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder_space)
            .into(holder.binding.imgSpace)

        holder.itemView.setOnClickListener {
            onImageClick(imageUrl)
        }
    }

    override fun getItemCount(): Int = images.size

    fun submitList(newImages: List<String>) {
        images.clear()
        images.addAll(newImages)
        notifyDataSetChanged()
    }
}
