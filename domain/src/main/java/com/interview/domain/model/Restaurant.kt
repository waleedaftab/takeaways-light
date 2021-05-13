package com.interview.domain.model

data class Restaurant(
    val name: String,
    val status: String,
    val sortingValues: SortingValues,
) {
    var isFavorite: Boolean = false
}

data class SortingValues(
    val bestMatch: Double,
    val newest: Double,
    val ratingAverage: Double,
    val distance: Double,
    val popularity: Double,
    val averageProductPrice: Double,
    val deliveryCosts: Double,
    val minCost: Double
)
