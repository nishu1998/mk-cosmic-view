package com.mkstudio.cosmicview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkstudio.cosmicview.databinding.ActivityNeoWsBinding

class NeoWsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNeoWsBinding
    private val viewModel: NeoWsViewModel by viewModels()
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
