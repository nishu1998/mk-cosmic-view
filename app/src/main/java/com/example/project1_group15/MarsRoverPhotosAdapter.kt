package com.example.project1_group15

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project1_group15.databinding.ItemMarsRoverPhotoBinding

class MarsRoverPhotosAdapter(private var photos: List<MarsRoverPhoto>) :
    RecyclerView.Adapter<MarsRoverPhotosAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(private val binding: ItemMarsRoverPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: MarsRoverPhoto) {
            binding.tvPhotoTitle.text = "Rover: ${photo.rover.name}, Date: ${photo.earth_date}"
            Glide.with(binding.root.context)
                .load(convertToHttps(photo.img_src))
                .into(binding.ivPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemMarsRoverPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size

    fun updateData(newPhotos: List<MarsRoverPhoto>) {
        photos = newPhotos
        notifyDataSetChanged()
    }
}

fun convertToHttps(url: String): String {
    return if (url.startsWith("http://")) {
        url.replace("http://", "https://")
    } else {
        url
    }
}
