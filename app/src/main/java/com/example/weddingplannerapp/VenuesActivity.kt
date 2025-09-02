package com.example.weddingplannerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VenuesActivity : AppCompatActivity() {

    private lateinit var venuesAdapter: VenuesAdapter
    private lateinit var allVenues: List<Venue>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venues)

        val rvVenues = findViewById<RecyclerView>(R.id.rvVenues)
        val etMaxBudget = findViewById<EditText>(R.id.etMaxBudget)
        val etMinCapacity = findViewById<EditText>(R.id.etMinCapacity)
        val btnFilter = findViewById<Button>(R.id.btnFilter)

        // Sample static data
        allVenues = listOf(
            Venue("Grand Palace", "Delhi", 500000, 300),
            Venue("Royal Garden", "Mumbai", 350000, 200),
            Venue("Lake View Resort", "Udaipur", 800000, 500),
            Venue("Heritage Hall", "Jaipur", 200000, 150),
            Venue("Ocean Pearl", "Goa", 600000, 400),
            Venue("Mountain Retreat", "Manali", 400000, 250)
        )

        venuesAdapter = VenuesAdapter(allVenues)
        rvVenues.layoutManager = LinearLayoutManager(this)
        rvVenues.adapter = venuesAdapter

        btnFilter.setOnClickListener {
            val maxBudgetText = etMaxBudget.text.toString()
            val minCapacityText = etMinCapacity.text.toString()

            val maxBudget = if (maxBudgetText.isNotEmpty()) maxBudgetText.toInt() else Int.MAX_VALUE
            val minCapacity = if (minCapacityText.isNotEmpty()) minCapacityText.toInt() else 0

            val filteredList = allVenues.filter { venue ->
                venue.priceRange <= maxBudget && venue.capacity >= minCapacity
            }

            venuesAdapter.updateData(filteredList)
        }
    }
}
