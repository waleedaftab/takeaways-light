package com.interview.data.datasource.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteRestaurant(
    @PrimaryKey @ColumnInfo(name = "restaurant_name") val restaurantName: String
)