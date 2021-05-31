package com.interview.takeawayslight.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.interview.takeawayslight.restaurantdetails.RestaurantDetailsViewModel
import com.interview.takeawayslight.restaurantlist.RestaurantListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RestaurantListViewModel::class)
    abstract fun bindRestaurantListViewModel(restaurantListViewModel: RestaurantListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantDetailsViewModel::class)
    abstract fun bindRestaurantDetailsViewModel(restaurantDetailsViewModel: RestaurantDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
