package com.example.market.domain.search

import com.example.market.domain.search.model.Address
import kotlinx.coroutines.flow.Flow

interface LocationSearchInteractor {

    suspend fun getAddress(token: String, query: String): Flow<Pair<List<Address>?, String?>>

}