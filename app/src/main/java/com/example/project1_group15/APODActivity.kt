package com.example.project1_group15

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.project1_group15.databinding.ActivityApodactivityBinding

class APODActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApodactivityBinding
    private val viewModel: ApodViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApodactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔄 Start loading state
        showLoading()

        // 👀 Observe APOD result
        viewModel.apodResult.observe(this) { result ->
            result.onSuccess { apod ->

                binding.tvApodTitle.text = apod.title
                binding.tvApodExplanation.text = apod.explanation

                if (apod.media_type == "image" && !apod.url.isNullOrEmpty()) {

                    binding.ivApodImage.visibility = View.VISIBLE

                    Glide.with(this)
                        .load(apod.hdurl ?: apod.url)
                        .placeholder(R.drawable.ic_placeholder_space)
                        .error(R.drawable.ic_placeholder_space)
                        .listener(object : RequestListener<android.graphics.drawable.Drawable> {

                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<android.graphics.drawable.Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                showContent()
                                return false
                            }

                            override fun onResourceReady(
                                resource: android.graphics.drawable.Drawable?,
                                model: Any?,
                                target: Target<android.graphics.drawable.Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                showContent()
                                return false
                            }
                        })
                        .into(binding.ivApodImage)

                } else {
                    // 🎥 Video or unsupported media
                    binding.ivApodImage.visibility = View.GONE
                    binding.tvApodExplanation.append(
                        "\n\n⚠️ Today's Astronomical Picture of the Day is a video. Please view it on NASA's official website."
                    )
                    showContent()
                }

            }.onFailure {
                showContent()
                Toast.makeText(
                    this,
                    "Failed to load Astronomical Picture of the Day.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        // 🚀 Trigger API call
        viewModel.loadApod()
    }

    // 🔄 Loading state
    private fun showLoading() {
        binding.apodShimmer.visibility = View.VISIBLE
        binding.apodContent.visibility = View.GONE
    }

    // ✅ Content ready
    private fun showContent() {
        binding.apodShimmer.visibility = View.GONE
        binding.apodContent.visibility = View.VISIBLE
    }
}
