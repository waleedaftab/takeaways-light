package com.interview.takeawayslight.restaurantlist

import com.interview.domain.interactors.SortingOrder
import com.interview.domain.model.Restaurant
import com.interview.takeawayslight.core.BaseView
import com.interview.takeawayslight.model.RestaurantViewModel

interface RestaurantListMVP : BaseView {
    fun showRestaurants(restaurants: List<RestaurantViewModel>)
    fun showSortingOptions(sortingOptions: List<SortingOrder>, selectedIndex: Int)
    fun showError(errorMessage: String)
    fun goToRestaurantDetailsActivity(restaurantName: String)
}