package com.interview.domain.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface FavoritesRepository {
    fun getAllFavorites(): Single<List<String>>
    fun addToFavorites(name: String): Completable
    fun removeFavorite(name: String): Completable
}