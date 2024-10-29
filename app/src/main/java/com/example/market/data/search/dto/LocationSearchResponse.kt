package com.example.market.data.search.dto

data class LocationSearchResponse(
    val suggestions: List<SuggestionDto>
): Response()