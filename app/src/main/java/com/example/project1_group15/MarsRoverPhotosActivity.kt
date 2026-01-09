package com.example.project1_group15

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1_group15.databinding.ActivityMarsRoverPhotosBinding
import android.widget.Toast


class MarsRoverPhotosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarsRoverPhotosBinding
    private val viewModel: MarsRoverPhotosViewModel by viewModels()
    private lateinit var adapter: MarsRoverPhotosAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarsRoverPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeMarsRoverPhotos()

        viewModel.loadMarsRoverPhotos()
    }

    private fun setupRecyclerView() {
        adapter = MarsRoverPhotosAdapter(emptyList())
        binding.rvMarsRoverPhotos.layoutManager = LinearLayoutManager(this)
        binding.rvMarsRoverPhotos.adapter = adapter
    }
    private fun observeMarsRoverPhotos() {
        viewModel.marsResult.observe(this) { result ->
            result.onSuccess { response ->
                adapter.updateData(response.photos)
            }.onFailure {
                Toast.makeText(
                    this,
                    "Failed to load Mars Rover photos. Please try again.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}


