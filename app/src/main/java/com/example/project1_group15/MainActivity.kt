package com.example.project1_group15

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project1_group15.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        binding.btnApod.setOnClickListener {
            startActivity(Intent(this, APODActivity::class.java))
        }

        binding.btnMarsRoverPhotos.setOnClickListener {
            startActivity(Intent(this, MarsRoverPhotosActivity::class.java))
        }

        binding.btnNeoWs.setOnClickListener {
            startActivity(Intent(this, NeoWsActivity::class.java))
        }

        binding.btnEpic.setOnClickListener {
            startActivity(Intent(this, EpicActivity::class.java))
        }

    }
}
