package com.example.project1_group15

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.project1_group15.databinding.ActivityImageViewerBinding
import android.transition.TransitionInflater
import androidx.core.view.ViewCompat


class ImageViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageViewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.sharedElementEnterTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)

        window.sharedElementReturnTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)

        binding = ActivityImageViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrl = intent.getStringExtra("image_url") ?: return

        ViewCompat.setTransitionName(binding.photoView, imageUrl)

        Glide.with(this)
            .load(imageUrl)
            .into(binding.photoView)

        binding.photoView.setOnClickListener {
            supportFinishAfterTransition()
        }
    }
}

