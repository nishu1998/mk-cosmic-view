package com.example.project1_group15

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1_group15.databinding.ActivityEpicBinding

class EpicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEpicBinding
    private val viewModel: EpicViewModel by viewModels()
    private lateinit var adapter: EpicImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpicBinding.inflate(layoutInflater)

        setupRecyclerView()
        observeEpicImages()

        // Fetch default EPIC images
        viewModel.fetchEpicImages()
    }

    private fun setupRecyclerView() {
        adapter = EpicImageAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeEpicImages() {
        viewModel.epicResult.observe(this) { result ->
            result.onSuccess { images ->
                adapter.submitList(images)
            }.onFailure {
                Toast.makeText(
                    this,
                    "Failed to load EPIC images. Please try again.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
