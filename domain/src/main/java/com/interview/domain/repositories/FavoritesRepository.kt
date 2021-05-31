package com.interview.domain.repositories

import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAllFavorites(): Flow<List<String>>
    suspend fun addToFavorites(name: String)
    suspend fun removeFavorite(name: String)
}