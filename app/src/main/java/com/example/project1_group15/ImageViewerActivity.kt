package com.example.project1_group15

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.example.project1_group15.databinding.ActivityImageViewerBinding

class ImageViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageViewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔁 Shared element transitions (KEEP AS-IS)
        window.sharedElementEnterTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)

        window.sharedElementReturnTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)

        binding = ActivityImageViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 📦 Get intent data
        val imageUrl = intent.getStringExtra("image_url") ?: return
        val imageTitle = intent.getStringExtra("image_title")

        // 🔗 Match transition name with source
        ViewCompat.setTransitionName(binding.photoView, imageUrl)

        // 🏷️ Optional overlay title
        binding.tvImageTitle.text = imageTitle ?: "Astronomical Image"

        // 🌌 Load image
        Glide.with(this)
            .load(imageUrl)
            .into(binding.photoView)

        // 👆 Tap to exit (UX-friendly)
        binding.photoView.setOnClickListener {
            supportFinishAfterTransition()
        }
    }
}
