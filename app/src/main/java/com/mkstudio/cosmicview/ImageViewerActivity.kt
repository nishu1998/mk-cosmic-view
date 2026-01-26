package com.mkstudio.cosmicview

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.transition.TransitionInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.mkstudio.cosmicview.databinding.ActivityImageViewerBinding
import com.google.android.material.snackbar.Snackbar

class ImageViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageViewerBinding
    private var downloadId: Long = -1L

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
            startDownloadWithSnackbar(imageUrl)
        }

        // 👆 Tap image to exit
        binding.photoView.setOnClickListener {
            supportFinishAfterTransition()
        }
    }

    // ===============================
    // DOWNLOAD WITH OPEN ACTION
    // ===============================
    private fun startDownloadWithSnackbar(imageUrl: String) {

        val request = DownloadManager.Request(Uri.parse(imageUrl))
            .setTitle("Downloading space image")
            .setDescription("Saving to Pictures")
            .setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            )
            .setAllowedOverMetered(true)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_PICTURES,
                "NASA_${System.currentTimeMillis()}.jpg"
            )

        val downloadManager =
            getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        downloadId = downloadManager.enqueue(request)

        // 🔔 Snackbar with OPEN action (reliable UX)
        val snackbar = Snackbar.make(
            binding.root,
            "Downloading image…",
            Snackbar.LENGTH_INDEFINITE
        ).setAction("OPEN") {
            openDownloadedImage(downloadManager)
        }

        snackbar.show()
        animateSnackbar(snackbar)

    }

    // ===============================
    // OPEN DOWNLOADED IMAGE
    // ===============================
    private fun openDownloadedImage(downloadManager: DownloadManager) {
        if (downloadId == -1L) {
            Toast.makeText(this, "No download found", Toast.LENGTH_SHORT).show()
            return
        }

        val uri = downloadManager.getUriForDownloadedFile(downloadId)

        if (uri != null) {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "image/*")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                "Image is still downloading…",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun animateSnackbar(snackbar: Snackbar) {
        val view = snackbar.view
        view.alpha = 0f
        view.translationY = view.height.toFloat()

        view.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(300)
            .setInterpolator(android.view.animation.DecelerateInterpolator())
            .start()
    }


}
