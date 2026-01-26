package com.mkstudio.cosmicview

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.mkstudio.cosmicview.databinding.ActivitySpaceGalleryBinding
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

        viewModel.loadGalleryImages()
    }

    private fun setupRecyclerView() {
        adapter = SpaceGalleryAdapter()
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
                delay(400)
                if (query.isEmpty()) {
                    viewModel.loadGalleryImages()
                } else {
                    viewModel.loadGalleryImages(query)
                }
            }
        }
    }
}
