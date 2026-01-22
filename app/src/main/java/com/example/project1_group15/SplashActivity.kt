package com.example.project1_group15

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.project1_group15.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    // ✅ Preload Home API silently
    private val homeViewModel: NasaImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔹 Start preloading Home data
        preloadHomeData()

        // 🔹 Play branding animation
        playSplashVideo()
    }

    private fun preloadHomeData() {
        // This warms up the API & cache before Home opens
        homeViewModel.loadHeroImage()
    }

    private fun playSplashVideo() {
        val videoUri = Uri.parse(
            "android.resource://${packageName}/${R.raw.mk_logo_reel}"
        )

        binding.videoView.setVideoURI(videoUri)

        binding.videoView.start()

        // 🎬 When animation finishes → Home
        binding.videoView.setOnCompletionListener {
            navigateToHome()
        }

        // 🛡️ Fallback if video fails
        binding.videoView.setOnErrorListener { _, _, _ ->
            navigateToHome()
            true
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}
