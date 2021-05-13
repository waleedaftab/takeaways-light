package com.interview.takeawayslight.restaurantdetails

import com.interview.data.mapper.Mapper
import com.interview.domain.interactors.RestaurantDetailInteractor
import com.interview.domain.model.Restaurant
import com.interview.domain.model.SortingValues
import com.interview.takeawayslight.core.SchedulersFactory
import com.interview.takeawayslight.model.RestaurantDetailsViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RestaurantDetailsPresenterImplTest {
    @Mock
    lateinit var view: RestaurantDetailsMVP

    @Mock
    lateinit var restaurantDetailInteractor: RestaurantDetailInteractor

    @Mock
    lateinit var schedulersFactory: SchedulersFactory

    @Mock
    lateinit var restaurantToRestaurantDetailsMapper: Mapper<Restaurant, RestaurantDetailsViewModel>

    private val testScheduler = TestScheduler()

    lateinit var presenter: RestaurantDetailsPresenterImpl

    @Before
    fun setup() {
        whenever(schedulersFactory.io()).thenReturn(testScheduler)
        whenever(schedulersFactory.main()).thenReturn(testScheduler)
        presenter = RestaurantDetailsPresenterImpl(
            restaurantDetailInteractor,
            schedulersFactory,
            restaurantToRestaurantDetailsMapper
        )
        presenter.attachView(view)
    }

    @Test
    fun testInit() {
        whenever(restaurantDetailInteractor.getRestaurantDetails("restaurant")).thenReturn(Single.just(getRestaurant()))
        whenever(restaurantToRestaurantDetailsMapper.map(getRestaurant())).thenReturn(getRestaurantViewModel())

        presenter.init("restaurant")
        testScheduler.triggerActions()

        verify(view).showRestaurantDetails(getRestaurantViewModel())
    }

    private fun getRestaurantViewModel() = RestaurantDetailsViewModel(
        "restaurant",
        "open",
        true,
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        1.0
    )

    private fun getRestaurant() = Restaurant(
        "restaurant1",
        "open",
        SortingValues(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
    ).apply { isFavorite = true }
}