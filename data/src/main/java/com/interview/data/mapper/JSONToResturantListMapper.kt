package com.interview.data.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.interview.data.model.RestaurantEntity
import com.interview.data.model.Restaurants
import com.interview.data.model.SortingValuesEntity
import com.interview.domain.model.Restaurant
import com.interview.domain.model.SortingValues
import kotlin.Exception

class JSONToRestaurantListMapper(private val gson: Gson) : Mapper<String, List<Restaurant>> {
    override fun map(input: String): List<Restaurant> {
        val typeToken = object : TypeToken<Restaurants>() {}.type
        return try {
            gson.fromJson<Restaurants>(input, typeToken)
                .restaurants
                .map(RestaurantEntityToRestaurantMapper::map)
        } catch (ex: Exception) {
            throw Exception("error in parsing")
        }
    }

    object RestaurantEntityToRestaurantMapper : Mapper<RestaurantEntity, Restaurant> {
        override fun map(input: RestaurantEntity): Restaurant {
            return Restaurant(
                input.name,
                input.status,
                SortingValuesEntityEntityToSortingValuesMapper.map(input.sortingValues)
            )
        }
    }

    object SortingValuesEntityEntityToSortingValuesMapper :
        Mapper<SortingValuesEntity, SortingValues> {
        override fun map(input: SortingValuesEntity): SortingValues {
            return SortingValues(
                input.bestMatch,
                input.newest,
                input.ratingAverage,
                input.distance,
                input.popularity,
                input.averageProductPrice,
                input.deliveryCosts,
                input.minCost
            )
        }
    }
}