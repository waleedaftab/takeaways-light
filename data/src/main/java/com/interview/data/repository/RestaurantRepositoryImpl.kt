package com.interview.data.repository

import com.interview.data.datasource.filesource.AssetFileSource
import com.interview.data.mapper.Mapper
import com.interview.domain.model.Restaurant
import com.interview.domain.repositories.RestaurantRepository
import io.reactivex.rxjava3.core.Single

class RestaurantRepositoryImpl(
    private val assertFileResource: AssetFileSource,
    private val mapper: Mapper<String, List<Restaurant>>
) : RestaurantRepository {
    override fun getRestaurantList(): Single<List<Restaurant>> =
        Single.fromCallable { mapper.map(assertFileResource.readAssetsFile(assertResource)) }

    override fun getRestaurantDetail(restaurantName: String): Single<Restaurant> =
        getRestaurantList().map { it.find { it.name == restaurantName } }

    companion object {
        private const val assertResource = "restaurants.json"
    }
}