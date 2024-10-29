package com.example.market.domain.search.impl

import com.example.market.domain.search.LocationSearchInteractor
import com.example.market.domain.search.LocationSearchRepository
import com.example.market.domain.search.model.Address
import com.example.market.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationSearchInteractorImpl(
    private val repository: LocationSearchRepository
): LocationSearchInteractor {
    override fun getAddress(token: String, query: String): Flow<Pair<List<Address>?, String?>> {
        return repository.getAddress(token, query).map { result ->
            when (result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }
                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }

}