package com.interview.domain.interactors

import com.interview.domain.model.Restaurant
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface RestaurantListInteractor {
    fun getRestaurantList(sortingOrder: SortingOrder): Single<List<Restaurant>>
    fun getSortOptions(): List<SortingOrder>
    fun addFavorite(restaurantName: String): Completable
    fun removeFavorite(restaurantName: String): Completable
    fun getFavorites(): Single<List<String>>
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
