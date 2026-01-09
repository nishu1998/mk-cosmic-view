package com.example.project1_group15

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1_group15.databinding.ActivityNeoWsBinding
import android.widget.Toast

class NeoWsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNeoWsBinding
    private lateinit var viewModel: NeoWsViewModel
    private lateinit var adapter: NeoWsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNeoWsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeNeoWs()

        viewModel.fetchNeoWsData(
            apiKey = "gfWcMDTnSlpSMxy2Qog4KccxZenNuyT5m9i42pdj"
        )
    }
    private fun setupRecyclerView() {
        adapter = NeoWsAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeNeoWs() {
        viewModel.neoResult.observe(this) { result ->
            result.onSuccess { neoList ->
                adapter.submitList(neoList)
            }.onFailure {
                Toast.makeText(
                    this,
                    "Failed to load asteroid data.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
