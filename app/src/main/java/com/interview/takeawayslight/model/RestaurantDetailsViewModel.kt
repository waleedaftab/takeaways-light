package com.interview.takeawayslight.model

data class RestaurantDetailsViewModel(
    val name: String,
    val status: String,
    val isFavorite: Boolean,
    val bestMatch: Double,
    val newest: Double,
    val ratingAverage: Double,
    val distance: Double,
    val popularity: Double,
    val averageProductPrice: Double,
    val deliveryCosts: Double,
    val minCost: Double
)
