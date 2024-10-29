package com.example.market.data.mapper

import com.example.market.data.search.dto.LocationSearchResponse
import com.example.market.domain.search.model.Address

class LocationResponseMapper() {

    fun map(response: LocationSearchResponse): List<Address> {
        return response.suggestions.map { address ->
            Address(
                value = address.value ?: "",
                unrestrictedValue = address.unrestricted_value ?: "",
            )
        }
    }
}