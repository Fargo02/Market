package com.example.market.ui.home.ui.section

import androidx.recyclerview.widget.RecyclerView
import com.example.market.databinding.SectionItemBinding
import com.example.market.domain.model.Section

class SectionsViewHolder(
    private val binding: SectionItemBinding,
    private val listener: SectionsAdapter.SectionClickListener
): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Section) {
        binding.name.text = model.name
        binding.cover.setImageResource(model.cover)

        itemView.setOnClickListener { listener.onSectionClick(model) }
    }
}