package com.example.project1_group15

import android.os.Bundle
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
                // TEMP: image loading will be guarded next step
                Glide.with(this)
                    .load(apod.url)
                    .into(binding.ivApodImage)
            }.onFailure {
                Toast.makeText(
                    this,
                    "Unable to load Astrological picture of the day. Please check your internet connection.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        // Trigger API call
        viewModel.loadApod()
    }
}
