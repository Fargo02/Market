package com.example.market.ui.home.view_model

import com.example.market.domain.search.model.Address

sealed interface SearchState {

    //data object Loading : SearchState

    data class Content(
        val addresses: List<Address>
    ) : SearchState

    data object Error : SearchState

    data object Empty : SearchState
}