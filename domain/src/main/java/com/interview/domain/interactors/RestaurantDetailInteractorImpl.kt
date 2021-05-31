package com.interview.domain.interactors

import com.interview.domain.model.Restaurant
import com.interview.domain.repositories.FavoritesRepository
import com.interview.domain.repositories.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip

class RestaurantDetailInteractorImpl(
    private val restaurantRepository: RestaurantRepository,
    private val favoritesRepository: FavoritesRepository
) : RestaurantDetailInteractor {
    override fun getRestaurantDetails(restaurantName: String): Flow<Restaurant> {
        return restaurantRepository.getRestaurantDetail(restaurantName).zip(
            favoritesRepository.getAllFavorites()
        ) { restaurant, favorites ->
            restaurant.apply { isFavorite = favorites.contains(name) }
        }
    }
}