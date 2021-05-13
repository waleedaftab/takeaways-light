package com.interview.domain.repositories

import com.interview.domain.model.Restaurant
import io.reactivex.rxjava3.core.Single

interface RestaurantRepository {
    fun getRestaurantList(): Single<List<Restaurant>>
    fun getRestaurantDetail(restaurantName: String): Single<Restaurant>
}