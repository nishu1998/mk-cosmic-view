package com.example.project1_group15

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.project1_group15.databinding.ActivitySpaceGalleryBinding

class SpaceGalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpaceGalleryBinding
    private val viewModel: SpaceGalleryViewModel by viewModels()
    private lateinit var adapter: SpaceGalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpaceGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeGallery()
        viewModel.loadGalleryImages()
    }

    private fun setupRecyclerView() {
        adapter = SpaceGalleryAdapter()
        binding.rvGallery.layoutManager = GridLayoutManager(this, 2)
        binding.rvGallery.adapter = adapter
    }

    private fun observeGallery() {
        viewModel.images.observe(this) { imageList ->
            adapter.submitList(imageList)
        }
    }
}
