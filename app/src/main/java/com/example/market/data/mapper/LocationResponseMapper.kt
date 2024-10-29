package com.example.market.data.mapper

import com.example.market.data.search.dto.LocationSearchResponse
import com.example.market.domain.search.model.Address

class LocationResponseMapper() {

    fun map(response: LocationSearchResponse): List<Address> {
        return response.suggestions.map { address ->
            Address(
                value = address.value ?: "",
                street = address.data.street_with_type ?: "",
                house = address.data.house ?: "",
                country = address.data.country ?: "",
                city = address.data.city_with_type ?: "",
                houseType = address.data.house_type ?: "",
            )
        }
    }
}