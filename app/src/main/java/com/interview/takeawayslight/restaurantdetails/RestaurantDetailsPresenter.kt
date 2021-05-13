package com.interview.takeawayslight.restaurantdetails

import com.interview.takeawayslight.core.BasePresenter

interface RestaurantDetailsPresenter : BasePresenter<RestaurantDetailsMVP> {
    fun init(restaurantName: String)
}