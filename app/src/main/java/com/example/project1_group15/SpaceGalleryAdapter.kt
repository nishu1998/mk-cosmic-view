package com.example.project1_group15

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project1_group15.databinding.ItemSpaceImageBinding
import androidx.core.app.ActivityOptionsCompat
import androidx.appcompat.app.AppCompatActivity


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

        holder.binding.imgSpace.transitionName = imageUrl

        holder.binding.imgSpace.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ImageViewerActivity::class.java)
            intent.putExtra("image_url", imageUrl)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as AppCompatActivity,
                holder.binding.imgSpace,
                imageUrl
            )

            context.startActivity(intent, options.toBundle())
        }

    }

    override fun getItemCount(): Int = images.size

    fun submitList(newImages: List<String>) {
        images.clear()
        images.addAll(newImages)
        notifyDataSetChanged()
    }
}
