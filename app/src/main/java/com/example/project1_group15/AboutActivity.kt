package com.example.project1_group15

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project1_group15.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAboutApp.text = "About the App"
        binding.tvAppDescription.text = "This app provides daily space images and information from NASA."
        binding.tvGroupMembers.text = "Group Members: Nishant Gautam, Deepak, Amaar Yasir Channa, Kiran Kumar Chaudhary"
    }
}
