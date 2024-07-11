package com.example.project1_group15

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1_group15.databinding.ActivityMarsRoverPhotosBinding

class MarsRoverPhotosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarsRoverPhotosBinding
    private val viewModel: MarsRoverPhotosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarsRoverPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MarsRoverPhotosAdapter(emptyList())

        binding.rvMarsRoverPhotos.layoutManager = LinearLayoutManager(this)
        binding.rvMarsRoverPhotos.adapter = adapter

        viewModel.marsRoverPhotos.observe(this) { photos ->
            adapter.updateData(photos)
        }

        viewModel.loadMarsRoverPhotos()
    }
}
