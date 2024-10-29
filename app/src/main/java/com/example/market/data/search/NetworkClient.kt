package com.example.market.data.search

import com.example.market.data.search.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}