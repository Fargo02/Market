package com.example.market.ui.home.ui.promotion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.market.databinding.PromotionalItemBinding
import com.example.market.domain.model.Promotional

class PromotionsAdapter(
    private val clickListener: PromotionClickListener
): RecyclerView.Adapter<PromotionsViewHolder>() {

    var promotions = ArrayList<Promotional>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionsViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return PromotionsViewHolder(PromotionalItemBinding.inflate(layoutInspector, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: PromotionsViewHolder, position: Int) {
        holder.bind(promotions[position])
    }

    override fun getItemCount(): Int {
        return promotions.size
    }

    interface PromotionClickListener {
        fun onPlusClicked(model: Promotional)
        fun onMinusClicked(model: Promotional)
    }

}
