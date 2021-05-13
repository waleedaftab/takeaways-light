package com.interview.data.datasource.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface FavoriteRestaurantDao {
    @Query("SELECT * from favorites")
    fun getFavoriteRestaurants(): Single<List<FavoriteRestaurant>>

    @Insert
    fun insert(favoriteRestaurant: FavoriteRestaurant): Completable

    @Delete
    fun remove(favoriteRestaurant: FavoriteRestaurant): Completable
}