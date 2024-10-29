package com.example.market.ui.home.ui.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.market.databinding.CatalogItemBinding
import com.example.market.domain.model.Catalog

class CatalogsAdapter(
    private val clickListener: CatalogClickListener
): RecyclerView.Adapter<CatalogsViewHolder>() {

    var catalogs = ArrayList<Catalog>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogsViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return CatalogsViewHolder(CatalogItemBinding.inflate(layoutInspector, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: CatalogsViewHolder, position: Int) {
        holder.bind(catalogs[position])
    }

    override fun getItemCount(): Int {
        return catalogs.size
    }

    fun interface CatalogClickListener {
        fun onCatalogClick(catalog: Catalog)
    }
}