package com.interview.domain.interactors

import com.interview.domain.model.Restaurant
import com.interview.domain.repositories.FavoritesRepository
import com.interview.domain.repositories.RestaurantRepository
import io.reactivex.rxjava3.core.Single

class RestaurantDetailInteractorImpl(
    private val restaurantRepository: RestaurantRepository,
    private val favoritesRepository: FavoritesRepository
) : RestaurantDetailInteractor {
    override fun getRestaurantDetails(restaurantName: String): Single<Restaurant> {
        return Single.zip(
            restaurantRepository.getRestaurantDetail(restaurantName),
            favoritesRepository.getAllFavorites(),
            { restaurant, favorites ->
                restaurant.apply { isFavorite = favorites.contains(name) }
            })
    }
}