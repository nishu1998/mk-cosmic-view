package com.example.project1_group15

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.project1_group15.databinding.ActivityImageViewerBinding

class ImageViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageViewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_placeholder_space)
            .into(binding.photoView)

        // Tap to exit (UX-friendly)
        binding.photoView.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val EXTRA_IMAGE_URL = "extra_image_url"
    }
}
