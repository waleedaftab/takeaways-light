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
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RestaurantDetailInteractorImplTest {

    @Mock
    lateinit var restaurantRepository: RestaurantRepository

    @Mock
    lateinit var favoritesRepository: FavoritesRepository

    lateinit var interactor: RestaurantDetailInteractorImpl

    @Before
    fun setUp() {
        interactor = RestaurantDetailInteractorImpl(restaurantRepository, favoritesRepository)
    }

    @Test
    fun getRestaurantDetails() {
        whenever(restaurantRepository.getRestaurantDetail("restaurant")).thenReturn(
            Single.just(
                getRestaurant()
            )
        )
        whenever(favoritesRepository.getAllFavorites()).thenReturn(Single.just(getFavorites()))

        val testSubscription = interactor.getRestaurantDetails("restaurant").test()

        testSubscription
            .assertComplete()
            .assertValue { it.isFavorite }
    }

    private fun getRestaurant() =
        Restaurant(
            "restaurant",
            "open",
            SortingValues(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        )

    private fun getFavorites() = listOf("restaurant1", "restaurant")
}