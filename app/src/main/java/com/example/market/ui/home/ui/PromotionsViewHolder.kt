package com.example.market.ui.home.ui

import android.graphics.Paint
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.market.databinding.PromotionalItemBinding
import com.example.market.domain.model.Promotional


class PromotionsViewHolder(
    private val binding: PromotionalItemBinding,
    private val listener: PromotionsAdapter.PromotionClickListener
): RecyclerView.ViewHolder(binding.root) {

    private var count = 0

    fun bind(model: Promotional) {
        binding.cover.setImageResource(model.cover)
        binding.name.text = model.name
        binding.wight.text = model.wight
        binding.lastCost.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            text = model.lastCoast
        }
        binding.newCost.text = model.newCoast
        binding.newBar.isVisible = model.isNew
        binding.discount.text = model.discount

        binding.plusBar.setOnClickListener {
            binding.activeGroup.isVisible = true
            binding.notActiveGroup.isVisible = false
            count++
            binding.count.text = count.toString()
            listener.onPlusClicked(model)
        }

        binding.plus.setOnClickListener {
            count++
            binding.count.text = count.toString()
            listener.onPlusClicked(model)
        }

        binding.minus.setOnClickListener {
            count--
            if (count == 0) {
                binding.activeGroup.isVisible = false
                binding.notActiveGroup.isVisible = true
            }
            binding.count.text = count.toString()
            listener.onMinusClicked(model)
        }

    }
}