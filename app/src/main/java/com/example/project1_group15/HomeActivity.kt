package com.example.project1_group15

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.project1_group15.databinding.ActivityHomeBinding
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.activity.viewModels
import com.bumptech.glide.Glide


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private val unsplashViewModel: UnsplashViewModel by viewModels()
    private val nasaImageViewModel: NasaImageViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout

        setupMenuButton()
        setupDrawerNavigation()
        setupApodClick()
        setupCardClicks()
        observeHeroImage()
        unsplashViewModel.loadHeroImage()
        loadNasaHeroImage()

    }

    private fun setupMenuButton() {
        binding.btnMenu.setOnClickListener {
            drawerLayout.open()

        }
    }

    // 📋 Drawer menu navigation
    private fun setupDrawerNavigation() {
        binding.navigationView.setNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.nav_home -> {
                    // Already on Home
                }

                R.id.nav_apod -> {
                    startActivity(Intent(this, APODActivity::class.java))
                }

                R.id.nav_epic -> {
                    startActivity(Intent(this, EpicActivity::class.java))
                }

                R.id.nav_neows -> {
                    startActivity(Intent(this, NeoWsActivity::class.java))
                }

                R.id.nav_gallery -> {
                    Toast.makeText(this, "Space Gallery coming soon 🖼️", Toast.LENGTH_SHORT).show()
                }

                R.id.nav_news -> {
                    Toast.makeText(this, "Space News coming soon 📰", Toast.LENGTH_SHORT).show()
                }

                R.id.nav_about -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    // 🌌 APOD hero image click
    private fun setupApodClick() {
        binding.imgApod.setOnClickListener {
            val intent = Intent(this, APODActivity::class.java)
            startActivity(intent)
        }
    }

    // 🧱 Home card clicks
    private fun setupCardClicks() {

        // 🖼️ Space Gallery (future feature)
        binding.cardGallery.setOnClickListener {
            Toast.makeText(this, "Space Gallery coming soon 🚀", Toast.LENGTH_SHORT).show()
        }

        // 🌍 Earth Today (EPIC)
        binding.cardEpic.setOnClickListener {
            startActivity(Intent(this, EpicActivity::class.java))
        }

        // 📅 Upcoming Events (future feature)
        binding.cardEvents.setOnClickListener {
            Toast.makeText(this, "Upcoming Events coming soon 📅", Toast.LENGTH_SHORT).show()
        }

        // 🚀 Latest Space News (future feature)
        binding.cardNews.setOnClickListener {
            Toast.makeText(this, "Space News coming soon 📰", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeHeroImage() {

        unsplashViewModel.heroImageUrl.observe(this) { imageUrl ->
            Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder_space)
                .into(binding.imgApod)
        }

        unsplashViewModel.errorMessage.observe(this) {
            // Optional: log only, no toast spam
        }
    }

    private fun loadNasaHeroImage() {

        nasaImageViewModel.loadHeroImage()

        nasaImageViewModel.heroImageUrl.observe(this) { imageUrl ->

            if (!imageUrl.isNullOrEmpty()) {

                Glide.with(this)
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder_space)
                    .error(R.drawable.ic_placeholder_space)
                    .into(binding.imgApod)

            }
        }
    }


}