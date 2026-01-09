package com.example.project1_group15

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.project1_group15.databinding.ActivityApodactivityBinding

class APODActivity :AppCompatActivity(){

    private lateinit var binding: ActivityApodactivityBinding
    private val viewModel: ApodViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApodactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe Result<ApodResponse> instead of ApodResponse
        viewModel.apodResult.observe(this) { result ->
            result.onSuccess { apod ->
                binding.tvApodTitle.text = apod.title
                binding.tvApodExplanation.text = apod.explanation
                if (apod.media_type == "image" && !apod.url.isNullOrEmpty()) {
                    binding.ivApodImage.visibility = View.VISIBLE

                    Glide.with(this)
                        .load(apod.url)
                        .into(binding.ivApodImage)

                } else {
                    // Video or unsupported media
                    binding.ivApodImage.visibility = View.GONE
                    binding.tvApodExplanation.append(
                        "\n\n⚠️ Today's Astronomical picture of the day is a video. Please view it on NASA's official website."
                    )
                }

            }.onFailure {
                Toast.makeText(
                    this,
                    "Failed to load Astronomical picture of the day. Please check your internet connection.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        // Trigger API call
        viewModel.loadApod()
    }
}
