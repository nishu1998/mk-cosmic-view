package com.example.project1_group15

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project1_group15.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playSplashVideo()
    }

    private fun playSplashVideo() {
        val videoUri = Uri.parse(
            "android.resource://${packageName}/${R.raw.mk_logo_reel}"
        )

        binding.videoView.setVideoURI(videoUri)

        // ✅ STEP 3: Scale video properly (no stretch)
        binding.videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = false

            val videoRatio =
                mediaPlayer.videoWidth.toFloat() / mediaPlayer.videoHeight.toFloat()
            val viewRatio =
                binding.videoView.width.toFloat() / binding.videoView.height.toFloat()

            val scale = videoRatio / viewRatio

            if (scale >= 1f) {
                binding.videoView.scaleX = scale
            } else {
                binding.videoView.scaleY = 1f / scale
            }

            binding.videoView.start()
        }

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

        // ✅ STEP 4: Smooth fade transition
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )

        finish()
    }
}
