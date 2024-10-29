package com.example.market.domain.search

import com.example.market.domain.search.model.Address
import com.example.market.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LocationSearchRepository {

    fun getAddress(token: String, query: String): Flow<Resource<List<Address>>>

}