package com.interview.takeawayslight.di

import com.interview.takeawayslight.restaurantdetails.RestaurantDetailsActivity
import com.interview.takeawayslight.restaurantlist.RestaurantListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeRestaurantListActivity(): RestaurantListActivity

    @ContributesAndroidInjector
    abstract fun contributeRestaurantDetailsActivity(): RestaurantDetailsActivity
}