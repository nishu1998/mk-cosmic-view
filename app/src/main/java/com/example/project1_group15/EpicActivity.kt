package com.example.project1_group15

import android.os.Bundle
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
        setContentView(binding.root)

        setupRecyclerView()

        viewModel.epicImages.observe(this) { images ->
            adapter.submitList(images)
        }

        // Fetch default EPIC images
        viewModel.fetchEpicImages()
    }

    private fun setupRecyclerView() {
        adapter = EpicImageAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}
