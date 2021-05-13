package com.interview.takeawayslight.di

import android.content.res.Resources
import com.interview.data.mapper.Mapper
import com.interview.domain.interactors.RestaurantListInteractor
import com.interview.domain.model.Restaurant
import com.interview.takeawayslight.core.SchedulersFactory
import com.interview.takeawayslight.mapper.RestaurantToRestaurantViewModelMapper
import com.interview.takeawayslight.model.RestaurantViewModel
import com.interview.takeawayslight.restaurantlist.*
import dagger.Module
import dagger.Provides

@Module
class RestaurantListActivityModule {

    @Provides
    @ActivityScope
    fun providesRestaurantListAdapter(
        activity: RestaurantListActivity,
        presenter: RestaurantListPresenter
    ): RestaurantListAdapter =
        RestaurantListAdapter(
            activity.layoutInflater,
            presenter::restaurantClicked,
            presenter::favoriteClicked
        )

    @Provides
    @ActivityScope
    fun providesSortListAdapter(
        resources: Resources,
        activity: RestaurantListActivity,
        presenter: RestaurantListPresenter
    ): SortingListAdapter =
        SortingListAdapter(resources, activity.layoutInflater, presenter::sortingOrderSelected)

    @Provides
    @ActivityScope
    fun providesRestaurantToRestaurantViewModelMapper(): Mapper<Restaurant, RestaurantViewModel> =
        RestaurantToRestaurantViewModelMapper()

    @Provides
    @ActivityScope
    fun providesRestaurantListPresenter(
        interactor: RestaurantListInteractor,
        schedularsFactory: SchedulersFactory,
        restaurantToRestaurantViewModelMapper: Mapper<Restaurant, RestaurantViewModel>
    ): RestaurantListPresenter = RestaurantListPresenterImpl(
        schedularsFactory,
        interactor,
        restaurantToRestaurantViewModelMapper
    )
}