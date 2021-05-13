package com.interview.takeawayslight.restaurantlist.restaurantlist

import com.interview.data.mapper.Mapper
import com.interview.domain.interactors.RestaurantListInteractor
import com.interview.domain.interactors.SortingOrder
import com.interview.domain.model.Restaurant
import com.interview.domain.model.SortingValues
import com.interview.takeawayslight.core.SchedulersFactory
import com.interview.takeawayslight.model.RestaurantViewModel
import com.interview.takeawayslight.restaurantlist.RestaurantListMVP
import com.interview.takeawayslight.restaurantlist.RestaurantListPresenterImpl
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RestaurantListPresenterTest {
    @Mock
    lateinit var view: RestaurantListMVP

    @Mock
    lateinit var schedulersFactory: SchedulersFactory

    @Mock
    lateinit var restaurantListInteractor: RestaurantListInteractor

    @Mock
    lateinit var mapper: Mapper<Restaurant, RestaurantViewModel>

    private val testScheduler = TestScheduler()

    lateinit var presenter: RestaurantListPresenterImpl

    @Before
    fun setup() {
        whenever(schedulersFactory.io()).thenReturn(testScheduler)
        whenever(schedulersFactory.main()).thenReturn(testScheduler)
        presenter = RestaurantListPresenterImpl(
            schedulersFactory,
            restaurantListInteractor,
            mapper
        )
        presenter.attachView(view)
    }

    @Test
    fun testInit() {
        whenever(restaurantListInteractor.getSortOptions()).thenReturn(getSortOptions())
        whenever(restaurantListInteractor.getRestaurantList(SortingOrder.BEST_MATCH)).thenReturn(
            Single.just(getRestaurants())
        )
        whenever(restaurantListInteractor.getFavorites()).thenReturn(Single.just(listOf("restaurant1")))
        whenever(mapper.map(any())).thenReturn(getRestaurantViewModel())

        presenter.init()
        testScheduler.triggerActions()

        verify(view).showRestaurants(argThat {
            this.size == 3 && this[0] == getRestaurantViewModel()
        })
        verify(view).showSortingOptions(getSortOptions(), 0)
    }

    @Test
    fun testRestaurantClicked() {
        presenter.restaurantClicked(getRestaurantViewModel())

        testScheduler.triggerActions()

        verify(view).goToRestaurantDetailsActivity("restaurant")
    }

    @Test
    fun testFavoriteClicked_Favorite() {
        whenever(restaurantListInteractor.removeFavorite("restaurant")).thenReturn(Completable.complete())
        presenter.favoriteClicked(getRestaurantViewModel())

        testScheduler.triggerActions()

        verify(restaurantListInteractor).removeFavorite("restaurant")
    }

    @Test
    fun testFavoriteClicked_NotFavorite() {
        whenever(restaurantListInteractor.addFavorite("restaurant")).thenReturn(Completable.complete())
        presenter.favoriteClicked(getRestaurantViewModel().copy(isFavorite = false))

        verify(restaurantListInteractor).addFavorite("restaurant")
    }

    private fun getRestaurantViewModel() = RestaurantViewModel("restaurant", "open", true)
    private fun getRestaurantViewModels() = listOf(
        RestaurantViewModel("restaurant1", "open", true),
        RestaurantViewModel("restaurant2", "closed", false),
        RestaurantViewModel("restaurant3", "open", false)
    )

    private fun getSortOptions() = listOf(
        SortingOrder.BEST_MATCH,
        SortingOrder.AVG_PRICE
    )

    private fun getRestaurants() = listOf(
        Restaurant(
            "restaurant1",
            "open",
            SortingValues(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        ).apply { isFavorite = true },
        Restaurant(
            "restaurant2",
            "closed",
            SortingValues(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        ),
        Restaurant(
            "restaurant3",
            "open ahead",
            SortingValues(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        )
    )
}