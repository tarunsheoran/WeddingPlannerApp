package com.example.weddingplannerapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weddingplannerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChecklist.setOnClickListener {
            startActivity(Intent(this, ChecklistActivity::class.java))
        }

        binding.btnVenues.setOnClickListener {
            startActivity(Intent(this, VenuesActivity::class.java))
        }
    }
}