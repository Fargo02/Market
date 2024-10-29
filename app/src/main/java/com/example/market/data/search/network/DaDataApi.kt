package com.example.market.data.search.network

import com.example.market.data.search.dto.LocationSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface DaDataApi {
    @GET("/suggestions/api/4_1/rs/suggest/address")
    fun getAddressSuggestions(
        @Header("Authorization") token: String,
        @Query("query") query: String): LocationSearchResponse
}