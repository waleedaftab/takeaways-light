package com.interview.data.repository

import com.interview.data.datasource.filesource.AssetFileSource
import com.interview.data.mapper.Mapper
import com.interview.domain.model.Restaurant
import com.interview.domain.repositories.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class RestaurantRepositoryImpl(
    private val assertFileResource: AssetFileSource,
    private val mapper: Mapper<String, List<Restaurant>>
) : RestaurantRepository {
    override fun getRestaurantList(): Flow<List<Restaurant>> =
        flowOf(mapper.map(assertFileResource.readAssetsFile(assertResource)))

    override fun getRestaurantDetail(restaurantName: String): Flow<Restaurant> =
        getRestaurantList().map { it.first { it.name == restaurantName } }

    companion object {
        private const val assertResource = "restaurants.json"
    }
}
