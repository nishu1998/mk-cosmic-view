package com.mkstudio.cosmicview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mkstudio.cosmicview.databinding.ActivityApodactivityBinding

class APODActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApodactivityBinding
    private val viewModel: ApodViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApodactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading()

        viewModel.apodResult.observe(this) { result ->

            result.onSuccess { apod ->

                // ✅ ALWAYS switch UI state first
                showContent()

                binding.tvApodTitle.text = apod.title
                binding.tvApodExplanation.text = apod.explanation

                if (apod.media_type == "image" && !apod.url.isNullOrEmpty()) {

                    binding.ivApodImage.visibility = View.VISIBLE

                    Glide.with(this)
                        .load(apod.hdurl ?: apod.url)
                        .placeholder(R.drawable.ic_placeholder_space)
                        .error(R.drawable.ic_placeholder_space)
                        .into(binding.ivApodImage)

                } else {
                    binding.ivApodImage.visibility = View.GONE
                    binding.tvApodExplanation.append(
                        "\n\n⚠️ Today's Astronomical Picture of the Day is a video."
                    )
                }
            }

            result.onFailure {
                showContent()
                Toast.makeText(
                    this,
                    "Failed to load Astronomical Picture of the Day",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        viewModel.loadApod()
    }

    private fun showLoading() {
        binding.apodShimmer.visibility = View.VISIBLE
        binding.apodContent.visibility = View.GONE
    }

    private fun showContent() {
        binding.apodShimmer.visibility = View.GONE
        binding.apodContent.visibility = View.VISIBLE
    }
}
