package com.example.weddingplannerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VenuesAdapter(private var venueList: List<Venue>) :
    RecyclerView.Adapter<VenuesAdapter.VenueViewHolder>() {

    inner class VenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvVenueName)
        val location: TextView = itemView.findViewById(R.id.tvVenueLocation)
        val price: TextView = itemView.findViewById(R.id.tvVenuePrice)
        val capacity: TextView = itemView.findViewById(R.id.tvVenueCapacity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_venue, parent, false)
        return VenueViewHolder(view)
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        val venue = venueList[position]
        holder.name.text = venue.name
        holder.location.text = venue.location
        holder.price.text = "₹${venue.priceRange}"
        holder.capacity.text = "${venue.capacity} Guests"
    }

    override fun getItemCount(): Int = venueList.size

    fun updateData(newList: List<Venue>) {
        venueList = newList
        notifyDataSetChanged()
    }


}
