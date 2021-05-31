package com.interview.data.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteRestaurant::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoriteRestaurantDao(): FavoriteRestaurantDao
}
