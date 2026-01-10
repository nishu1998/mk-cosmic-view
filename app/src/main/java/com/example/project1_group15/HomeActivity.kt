package com.example.project1_group15

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.project1_group15.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout

        setupMenuButton()
        setupApodClick()

    }

    private fun setupMenuButton() {
        binding.btnMenu.setOnClickListener {
            drawerLayout.open()
        }
    }

    private fun setupApodClick() {
        binding.imgApod.setOnClickListener {
            val intent = Intent(this, APODActivity::class.java)
            startActivity(intent)
        }
    }
}