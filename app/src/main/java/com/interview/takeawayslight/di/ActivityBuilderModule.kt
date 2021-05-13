package com.interview.takeawayslight.di

import com.interview.takeawayslight.restaurantdetails.RestaurantDetailsActivity
import com.interview.takeawayslight.restaurantlist.RestaurantListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [RestaurantListActivityModule::class])
    abstract fun contributeRestaurantListActivity(): RestaurantListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [RestaurantDetailsActivityModule::class])
    abstract fun contributeRestaurantDetailsActivity(): RestaurantDetailsActivity
}