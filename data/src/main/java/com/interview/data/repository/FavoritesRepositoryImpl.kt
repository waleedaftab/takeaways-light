package com.interview.data.repository

import com.interview.data.datasource.room.FavoriteRestaurant
import com.interview.data.datasource.room.FavoriteRestaurantDao
import com.interview.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(private val dao: FavoriteRestaurantDao) : FavoritesRepository {
    override fun getAllFavorites(): Flow<List<String>> =
        dao.getFavoriteRestaurants().map { it.map { it.restaurantName } }

    override suspend fun addToFavorites(name: String) = dao.insert(FavoriteRestaurant(name))

    override suspend fun removeFavorite(name: String) = dao.remove(FavoriteRestaurant(name))
}