package com.interview.domain.interactors

import com.interview.domain.model.Restaurant
import com.interview.domain.model.SortingValues
import com.interview.domain.repositories.FavoritesRepository
import com.interview.domain.repositories.RestaurantRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RestaurantListInteractorImplTest {

    @Mock
    lateinit var restaurantRepository: RestaurantRepository

    @Mock
    lateinit var favoritesRepository: FavoritesRepository

    private lateinit var interactor: RestaurantListInteractorImpl

    @Before
    fun setup() {
        interactor = RestaurantListInteractorImpl(restaurantRepository, favoritesRepository)
    }

    @Test
    fun testGetRestaurantList() {
        whenever(restaurantRepository.getRestaurantList()).thenReturn(Single.just(getRestaurants()))
        whenever(favoritesRepository.getAllFavorites()).thenReturn(Single.just(getFavorites()))

        val testSubscription = interactor.getRestaurantList(SortingOrder.MIN_COST).test()

        testSubscription.assertComplete()
            .assertValue {
                it.size == 4 && it[0].name == "favoriterestaurant"
                        && it[0].isFavorite && it[1].name == "mincost" && it[2].name == "restaurant1"
                        && it[3].status == "order ahead"
            }
    }

    @Test
    fun testAddFavorite() {
        interactor.addFavorite("restaurant")

        verify(favoritesRepository).addToFavorites("restaurant")
    }

    @Test
    fun testRemoveFavorite() {
        interactor.removeFavorite("restaurant")

        verify(favoritesRepository).removeFavorite("restaurant")
    }

    @Test
    fun testGetFavorites() {
        interactor.getFavorites()

        verify(favoritesRepository).getAllFavorites()
    }

    @Test
    fun getSortOptions() {
        assert(interactor.getSortOptions() == getExpectedSortOptionsList())
    }

    private fun getExpectedSortOptionsList() = listOf(
        SortingOrder.BEST_MATCH,
        SortingOrder.NEWEST,
        SortingOrder.RATING,
        SortingOrder.DISTANCE,
        SortingOrder.POPULARITY,
        SortingOrder.AVG_PRICE,
        SortingOrder.DELIVERY_COST,
        SortingOrder.MIN_COST
    )

    private fun getRestaurants() = listOf(
        Restaurant(
            "restaurant1",
            "open",
            SortingValues(8.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        ),
        Restaurant(
            "orderahead",
            "order ahead",
            SortingValues(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 1.0)
        ),
        Restaurant(
            "mincost",
            "open",
            SortingValues(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 1.0)
        ),
        Restaurant(
            "favoriterestaurant",
            "open",
            SortingValues(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        )
    )

    private fun getFavorites() = listOf("favoriterestaurant", "restaurant")
}