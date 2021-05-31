package com.interview.domain.repositories

import com.interview.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {
    fun getRestaurantList(): Flow<List<Restaurant>>
    fun getRestaurantDetail(restaurantName: String): Flow<Restaurant>
}