package com.interview.takeawayslight.di

import com.interview.data.mapper.Mapper
import com.interview.domain.interactors.RestaurantDetailInteractor
import com.interview.domain.model.Restaurant
import com.interview.takeawayslight.core.SchedulersFactory
import com.interview.takeawayslight.mapper.RestaurantToRestaurantDetailsViewModelMapper
import com.interview.takeawayslight.model.RestaurantDetailsViewModel
import com.interview.takeawayslight.restaurantdetails.RestaurantDetailsPresenter
import com.interview.takeawayslight.restaurantdetails.RestaurantDetailsPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class RestaurantDetailsActivityModule {

    @ActivityScope
    @Provides
    fun providesRestaurantToRestaurantDetailsViewModelMapper(): Mapper<Restaurant, RestaurantDetailsViewModel> {
        return RestaurantToRestaurantDetailsViewModelMapper()
    }

    @ActivityScope
    @Provides
    fun providesRestaurantDetailsPresenter(
        restaurantDetailInteractor: RestaurantDetailInteractor,
        schedulersFactory: SchedulersFactory,
        restaurantToRestaurantDetailsMapper: Mapper<Restaurant, RestaurantDetailsViewModel>
    ): RestaurantDetailsPresenter {
        return RestaurantDetailsPresenterImpl(
            restaurantDetailInteractor,
            schedulersFactory,
            restaurantToRestaurantDetailsMapper
        )
    }
}