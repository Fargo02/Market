package com.example.market.ui.home.ui.location

import androidx.recyclerview.widget.RecyclerView
import com.example.market.databinding.LocationItemBinding
import com.example.market.domain.search.model.Address

class LocationsViewHolder(
    private val binding: LocationItemBinding,
    private val listener: LocationsAdapter.LocationClickListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Address) {
        binding.value.text = model.value
        binding.unrestrictedValue.text = model.unrestrictedValue

        itemView.setOnClickListener { listener.onSectionClick(model) }
    }
}