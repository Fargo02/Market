package com.example.market.ui.home.ui.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.market.databinding.BannerItemBinding
import com.example.market.domain.model.Banner

class BannersAdapter(
    private val clickListener: SectionClickListener
): RecyclerView.Adapter<BannersViewHolder>() {

    var banners = ArrayList<Banner>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return BannersViewHolder(BannerItemBinding.inflate(layoutInspector, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        holder.bind(banners[position])
    }

    override fun getItemCount(): Int {
        return banners.size
    }

    fun interface SectionClickListener {
        fun onSectionClick(banner: Banner)
    }
}