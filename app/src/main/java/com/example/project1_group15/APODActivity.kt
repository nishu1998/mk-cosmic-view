package com.example.project1_group15

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.project1_group15.databinding.ActivityApodactivityBinding

class APODActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApodactivityBinding
    private val viewModel: ApodViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApodactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.apodData.observe(this) { apod ->
            binding.tvApodTitle.text = apod.title
            binding.tvApodExplanation.text = apod.explanation
            Glide.with(this).load(apod.url).into(binding.ivApodImage)
        }

        viewModel.loadApod()
    }
}
