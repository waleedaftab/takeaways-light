package com.interview.takeawayslight.restaurantdetails

import com.interview.takeawayslight.core.BaseView
import com.interview.takeawayslight.model.RestaurantDetailsViewModel

interface RestaurantDetailsMVP : BaseView {
    fun showRestaurantDetails(viewModel: RestaurantDetailsViewModel)
}