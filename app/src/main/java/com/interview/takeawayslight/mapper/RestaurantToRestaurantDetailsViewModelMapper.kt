package com.interview.takeawayslight.mapper

import com.interview.data.mapper.Mapper
import com.interview.domain.model.Restaurant
import com.interview.takeawayslight.model.RestaurantDetailsViewModel

class RestaurantToRestaurantDetailsViewModelMapper :
    Mapper<Restaurant, RestaurantDetailsViewModel> {
    override fun map(input: Restaurant): RestaurantDetailsViewModel {
        return RestaurantDetailsViewModel(
            name = input.name,
            status = input.status,
            isFavorite = input.isFavorite,
            bestMatch = input.sortingValues.bestMatch,
            newest = input.sortingValues.newest,
            ratingAverage = input.sortingValues.ratingAverage,
            distance = input.sortingValues.distance,
            popularity = input.sortingValues.popularity,
            averageProductPrice = input.sortingValues.averageProductPrice,
            deliveryCosts = input.sortingValues.deliveryCosts,
            minCost = input.sortingValues.minCost
        )
    }
}