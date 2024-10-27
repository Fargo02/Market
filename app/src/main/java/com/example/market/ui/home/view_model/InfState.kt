package com.example.market.ui.home.view_model

import com.example.market.domain.model.Banner
import com.example.market.domain.model.Catalog
import com.example.market.domain.model.Promotional
import com.example.market.domain.model.Section

sealed interface InfState {
    data class Content(
        val sections: List<Section>,
        val banners: List<Banner>,
        val promotions: List<Promotional>,
        val catalogs: List<Catalog>,
    ): InfState
}