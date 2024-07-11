package com.example.project1_group15

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1_group15.databinding.ActivityNeoWsBinding

class NeoWsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNeoWsBinding
    private lateinit var viewModel: NeoWsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNeoWsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(NeoWsViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NeoWsAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.neoWsList.observe(this, { neoWsList ->
            adapter.submitList(neoWsList)
        })

        viewModel.fetchNeoWsData("gfWcMDTnSlpSMxy2Qog4KccxZenNuyT5m9i42pdj")
    }
}
