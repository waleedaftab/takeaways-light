package com.interview.takeawayslight.mapper

import com.interview.data.mapper.Mapper
import com.interview.domain.model.Restaurant
import com.interview.takeawayslight.model.RestaurantDataModel

object RestaurantToRestaurantViewModelMapper : Mapper<Restaurant, RestaurantDataModel> {
    override fun map(input: Restaurant): RestaurantDataModel =
        RestaurantDataModel(input.name, input.status, input.isFavorite)
}