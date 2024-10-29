package com.example.market.ui.home.ui.section

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.market.databinding.SectionItemBinding
import com.example.market.domain.model.Section

class SectionsAdapter(
    private val clickListener: SectionClickListener
): RecyclerView.Adapter<SectionsViewHolder>() {

    var sections = ArrayList<Section>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionsViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return SectionsViewHolder(SectionItemBinding.inflate(layoutInspector, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: SectionsViewHolder, position: Int) {
        holder.bind(sections[position])
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    fun interface SectionClickListener {
        fun onSectionClick(section: Section)
    }
}