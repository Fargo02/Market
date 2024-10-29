package com.example.market.ui.home.ui.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.market.databinding.LocationItemBinding
import com.example.market.domain.search.model.Address

class LocationsAdapter(
    private val clickListener: LocationClickListener
): RecyclerView.Adapter<LocationsViewHolder>() {

    var locations = ArrayList<Address>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return LocationsViewHolder(LocationItemBinding.inflate(layoutInspector, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.bind(locations[position])
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    fun interface LocationClickListener {
        fun onSectionClick(location: Address)
    }
}