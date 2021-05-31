package com.interview.takeawayslight.di

import com.interview.data.mapper.Mapper
import com.interview.domain.model.Restaurant
import com.interview.takeawayslight.mapper.RestaurantToRestaurantDetailsViewModelMapper
import com.interview.takeawayslight.mapper.RestaurantToRestaurantViewModelMapper
import com.interview.takeawayslight.model.RestaurantDetailsDataModel
import com.interview.takeawayslight.model.RestaurantDataModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Singleton
    @Provides
    fun providesRestaurantToRestaurantDetailsViewModelMapper(): Mapper<Restaurant, RestaurantDetailsDataModel> =
        RestaurantToRestaurantDetailsViewModelMapper

    @Singleton
    @Provides
    fun providesRestaurantToRestaurantViewModelMapper(): Mapper<Restaurant, RestaurantDataModel> =
        RestaurantToRestaurantViewModelMapper
}