package com.example.project1_group15

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project1_group15.databinding.ItemSpaceImageBinding

class SpaceGalleryAdapter(
    private val images: MutableList<SpaceImage> = mutableListOf()
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
        val item = images[position]

        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder_space)
            .into(holder.binding.imgSpace)

        // Shared element transition name
        holder.binding.imgSpace.transitionName = item.imageUrl

        holder.binding.imgSpace.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ImageViewerActivity::class.java).apply {
                putExtra("image_url", item.imageUrl)
                putExtra("image_title", item.title)
            }

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as AppCompatActivity,
                holder.binding.imgSpace,
                item.imageUrl
            )

            context.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int = images.size

    fun submitList(newImages: List<SpaceImage>) {
        images.clear()
        images.addAll(newImages)
        notifyDataSetChanged()
    }
}
