package com.example.project1_group15

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.transition.TransitionInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.example.project1_group15.databinding.ActivityImageViewerBinding

class ImageViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageViewerBinding
    private var downloadId: Long = -1L

    private val downloadReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (id == downloadId) {
                Toast.makeText(
                    context,
                    "Download completed ✔ Saved to Pictures",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔁 Shared element transitions
        window.sharedElementEnterTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)

        window.sharedElementReturnTransition =
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)

        binding = ActivityImageViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrl = intent.getStringExtra("image_url") ?: return
        val imageTitle = intent.getStringExtra("image_title") ?: "Astronomical Image"

        // 🔗 Shared element mapping
        ViewCompat.setTransitionName(binding.photoView, imageUrl)

        // 🏷️ Title overlay
        binding.tvImageTitle.text = imageTitle

        // 🌌 Load image
        Glide.with(this)
            .load(imageUrl)
            .into(binding.photoView)

        // ⬇️ Download button
        binding.btnDownload.setOnClickListener {
            startDownload(imageUrl)
        }

        // 👆 Tap image to exit
        binding.photoView.setOnClickListener {
            supportFinishAfterTransition()
        }

        // ✅ FIXED receiver registration (Android 13+ safe)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(
                downloadReceiver,
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
                Context.RECEIVER_NOT_EXPORTED
            )
        } else {
            @Suppress("DEPRECATION")
            registerReceiver(
                downloadReceiver,
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
            )
        }

    }

    private fun startDownload(url: String) {
        Toast.makeText(this, "Downloading started…", Toast.LENGTH_SHORT).show()

        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("Downloading space image")
            .setDescription("Saving image to device")
            .setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            )
            .setAllowedOverMetered(true)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_PICTURES,
                "NASA_${System.currentTimeMillis()}.jpg"
            )

        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadId = manager.enqueue(request)
    }

    override fun onDestroy() {
        unregisterReceiver(downloadReceiver)
        super.onDestroy()
    }
}
