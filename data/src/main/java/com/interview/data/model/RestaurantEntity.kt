package com.interview.data.model

data class Restaurants(val restaurants: List<RestaurantEntity>)

data class RestaurantEntity(
    val name: String,
    val status: String,
    val sortingValues: SortingValuesEntity
)

data class SortingValuesEntity(
    val bestMatch: Double,
    val newest: Double,
    val ratingAverage: Double,
    val distance: Double,
    val popularity: Double,
    val averageProductPrice: Double,
    val deliveryCosts: Double,
    val minCost: Double
)