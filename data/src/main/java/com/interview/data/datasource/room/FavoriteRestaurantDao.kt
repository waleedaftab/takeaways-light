package com.interview.data.datasource.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRestaurantDao {
    @Query("SELECT * from favorites")
    fun getFavoriteRestaurants(): Flow<List<FavoriteRestaurant>>

    @Insert
    suspend fun insert(favoriteRestaurant: FavoriteRestaurant)

    @Delete
    suspend fun remove(favoriteRestaurant: FavoriteRestaurant)
}