package com.example.market.ui.home.ui.banner

import androidx.recyclerview.widget.RecyclerView
import com.example.market.databinding.BannerItemBinding
import com.example.market.domain.model.Banner

class BannersViewHolder(
    private val binding: BannerItemBinding,
    private val listener: BannersAdapter.SectionClickListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Banner) {
        binding.title.text = model.title
        binding.subtitle.text = model.subtitle
        binding.cover.setImageResource(model.cover)

        itemView.setOnClickListener { listener.onSectionClick(model) }
    }
}