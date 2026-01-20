package com.example.project1_group15

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.project1_group15.databinding.ActivitySpaceGalleryBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SpaceGalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpaceGalleryBinding
    private val viewModel: SpaceGalleryViewModel by viewModels()
    private lateinit var adapter: SpaceGalleryAdapter

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpaceGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeGallery()
        setupSearch()

        // Initial load
        viewModel.loadGalleryImages()
    }

    private fun setupRecyclerView() {
        adapter = SpaceGalleryAdapter { imageUrl ->
            val intent = Intent(this, ImageViewerActivity::class.java)
            intent.putExtra(ImageViewerActivity.EXTRA_IMAGE_URL, imageUrl)
            startActivity(intent)
        }

        binding.rvGallery.layoutManager = GridLayoutManager(this, 2)
        binding.rvGallery.adapter = adapter
    }


    private fun observeGallery() {
        viewModel.images.observe(this) { images ->
            adapter.submitList(images)
        }
    }

    private fun setupSearch() {
        binding.etSearch.doAfterTextChanged { editable ->
            val query = editable?.toString()?.trim().orEmpty()

            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(400) // debounce for better UX & fewer API calls

                if (query.isEmpty()) {
                    viewModel.loadGalleryImages()
                } else {
                    viewModel.loadGalleryImages(query)
                }
            }
        }
    }
}
