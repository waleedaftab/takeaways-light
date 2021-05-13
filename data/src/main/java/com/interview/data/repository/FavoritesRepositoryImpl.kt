package com.interview.data.repository

import com.interview.data.datasource.room.FavoriteRestaurant
import com.interview.data.datasource.room.FavoriteRestaurantDao
import com.interview.domain.repositories.FavoritesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class FavoritesRepositoryImpl(private val dao: FavoriteRestaurantDao) : FavoritesRepository {
    override fun getAllFavorites(): Single<List<String>> =
        dao.getFavoriteRestaurants().map { it.map { it.restaurantName } }

    override fun addToFavorites(name: String): Completable = dao.insert(FavoriteRestaurant(name))

    override fun removeFavorite(name: String): Completable = dao.remove(FavoriteRestaurant(name))
}