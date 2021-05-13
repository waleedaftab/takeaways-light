package com.interview.takeawayslight.mapper

import com.interview.data.mapper.Mapper
import com.interview.domain.model.Restaurant
import com.interview.takeawayslight.model.RestaurantViewModel

class RestaurantToRestaurantViewModelMapper : Mapper<Restaurant, RestaurantViewModel> {
    override fun map(input: Restaurant): RestaurantViewModel =
        RestaurantViewModel(input.name, input.status, input.isFavorite)
}