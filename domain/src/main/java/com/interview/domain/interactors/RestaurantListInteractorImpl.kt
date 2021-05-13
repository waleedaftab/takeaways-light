package com.interview.domain.interactors

import com.interview.domain.model.Restaurant
import com.interview.domain.repositories.FavoritesRepository
import com.interview.domain.repositories.RestaurantRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RestaurantListInteractorImpl(
    private val repository: RestaurantRepository,
    private val favoritesRepository: FavoritesRepository
) : RestaurantListInteractor {
    private val priority = listOf("open", "closed", "order ahead")
    override fun getRestaurantList(sortingOrder: SortingOrder): Single<List<Restaurant>> =
        Single.zip(
            repository.getRestaurantList(),
            favoritesRepository.getAllFavorites(),
            { restaurants, favorites ->
                restaurants
                    .map { it.apply { it.isFavorite = favorites.contains(it.name) } }
                    .sortBySortingOrder(sortingOrder)
                    .sortByStatus()
                    .sortedBy { !it.isFavorite }
            })

    private fun List<Restaurant>.sortByStatus(): List<Restaurant> {
        return this.sortedWith { restaurant1, restaurant2 ->
            priority.indexOf(restaurant1.status).compareTo(priority.indexOf(restaurant2.status))
        }
    }

    private fun List<Restaurant>.sortBySortingOrder(sortingOrder: SortingOrder): List<Restaurant> {
        return this.sortedBy { restaurant ->
            when (sortingOrder) {
                SortingOrder.BEST_MATCH -> restaurant.sortingValues.averageProductPrice
                SortingOrder.NEWEST -> restaurant.sortingValues.newest
                SortingOrder.RATING -> restaurant.sortingValues.ratingAverage
                SortingOrder.DISTANCE -> restaurant.sortingValues.distance
                SortingOrder.POPULARITY -> restaurant.sortingValues.popularity
                SortingOrder.AVG_PRICE -> restaurant.sortingValues.averageProductPrice
                SortingOrder.DELIVERY_COST -> restaurant.sortingValues.deliveryCosts
                SortingOrder.MIN_COST -> restaurant.sortingValues.minCost
            }
        }
    }

    override fun getSortOptions(): List<SortingOrder> {
        return SortingOrder.values().toList()
    }

    override fun addFavorite(restaurantName: String): Completable =
        favoritesRepository.addToFavorites(restaurantName)

    override fun removeFavorite(restaurantName: String): Completable =
        favoritesRepository.removeFavorite(restaurantName)

    override fun getFavorites(): Single<List<String>> = favoritesRepository.getAllFavorites()
}