package com.mkstudio.cosmicview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.mkstudio.cosmicview.databinding.ActivityHomeBinding
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.activity.viewModels
import com.bumptech.glide.Glide


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
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
        setupLetsGoCta()
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
                    refreshHome()
                }

                R.id.nav_apod -> {
                    startActivity(Intent(this, APODActivity::class.java))
                }

                R.id.nav_neows -> {
                    startActivity(Intent(this, NeoWsActivity::class.java))
                }

                R.id.nav_gallery -> {
                    startActivity(Intent(this, SpaceGalleryActivity::class.java))
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

    private fun setupLetsGoCta() {
        binding.ctaLetsGo.setOnClickListener {
            startActivity(Intent(this, APODActivity::class.java))
        }
    }

    // 🧱 Home card clicks
    private fun setupCardClicks() {

        // 🖼️ Space Gallery
        binding.cardGallery.setOnClickListener {
            startActivity(Intent(this, SpaceGalleryActivity::class.java))
        }

        // 📅 Upcoming Astronomical Events
        binding.cardEvents.setOnClickListener {
            startActivity(Intent(this, UpcomingEventsActivity::class.java))
        }

        // ☄️ Near-Earth Objects (NeoWs)
        binding.cardNeoWs.setOnClickListener {
            startActivity(Intent(this, NeoWsActivity::class.java))
        }

        // 🚀 Space News (future)
        binding.cardNews.setOnClickListener {
            Toast.makeText(this, "Space News coming soon 📰", Toast.LENGTH_SHORT).show()
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
    private fun refreshHome() {
        loadNasaHeroImage()
    }


}