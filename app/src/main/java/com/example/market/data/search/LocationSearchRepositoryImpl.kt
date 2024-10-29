package com.example.market.data.search

import com.example.market.data.mapper.LocationResponseMapper
import com.example.market.data.search.dto.LocationSearchRequest
import com.example.market.data.search.dto.LocationSearchResponse
import com.example.market.domain.search.LocationSearchRepository
import com.example.market.domain.search.model.Address
import com.example.market.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocationSearchRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: LocationResponseMapper,
): LocationSearchRepository {
    override suspend fun getAddress(token: String, query: String): Flow<Resource<List<Address>>> = flow {
        val response = networkClient.doRequest(LocationSearchRequest(token, query))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                val data = mapper.map(response as LocationSearchResponse)
                emit(Resource.Success(data))
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
}