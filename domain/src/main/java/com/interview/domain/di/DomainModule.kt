package com.interview.domain.di

import com.interview.domain.interactors.RestaurantDetailInteractor
import com.interview.domain.interactors.RestaurantDetailInteractorImpl
import com.interview.domain.interactors.RestaurantListInteractor
import com.interview.domain.interactors.RestaurantListInteractorImpl
import com.interview.domain.repositories.FavoritesRepository
import com.interview.domain.repositories.RestaurantRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    @Singleton
    fun provideRestaurantListInteractor(
        restaurantRepository: RestaurantRepository,
        favoritesRepository: FavoritesRepository
    ): RestaurantListInteractor =
        RestaurantListInteractorImpl(restaurantRepository, favoritesRepository)

    @Provides
    @Singleton
    fun provideRestaurantDetailInteractor(
        restaurantRepository: RestaurantRepository,
        favoritesRepository: FavoritesRepository
    ): RestaurantDetailInteractor =
        RestaurantDetailInteractorImpl(restaurantRepository, favoritesRepository)
}