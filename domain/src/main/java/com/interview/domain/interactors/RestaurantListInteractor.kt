package com.interview.domain.interactors

import com.interview.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantListInteractor {
    fun getRestaurantList(sortingOrder: SortingOrder): Flow<List<Restaurant>>
    fun getSortOptions(): List<SortingOrder>
    suspend fun addFavorite(restaurantName: String)
    suspend fun removeFavorite(restaurantName: String)
    fun getFavorites(): Flow<List<String>>
}

enum class SortingOrder(val title: String) {
    BEST_MATCH("Best Match"),
    NEWEST("Newest"),
    RATING("Average Rating"),
    DISTANCE("Distance"),
    POPULARITY("Popularity"),
    AVG_PRICE("Average Price"),
    DELIVERY_COST("Delivery Cost"),
    MIN_COST("Minimum Cost")
}
