package com.example.market.ui.home.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.market.databinding.CatalogItemBinding
import com.example.market.domain.model.Catalog

class CatalogsViewHolder(
    private val binding: CatalogItemBinding,
    private val listener: CatalogsAdapter.CatalogClickListener
): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Catalog) {
        binding.name.text = model.name
        binding.cover.setImageResource(model.cover)
        binding.cardView.setCardBackgroundColor(itemView.context.getColor(model.color))

        itemView.setOnClickListener { listener.onCatalogClick(model) }
    }
}