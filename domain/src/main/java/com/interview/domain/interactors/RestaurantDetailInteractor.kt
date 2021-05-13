package com.interview.domain.interactors

import com.interview.domain.model.Restaurant
import io.reactivex.rxjava3.core.Single

interface RestaurantDetailInteractor {
    fun getRestaurantDetails(restaurantName: String): Single<Restaurant>
}