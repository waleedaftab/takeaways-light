package com.interview.domain.interactors

import com.interview.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantDetailInteractor {
    fun getRestaurantDetails(restaurantName: String): Flow<Restaurant>
}