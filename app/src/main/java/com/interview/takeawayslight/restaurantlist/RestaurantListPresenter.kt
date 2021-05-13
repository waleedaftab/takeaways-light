package com.interview.takeawayslight.restaurantlist

import com.interview.domain.interactors.SortingOrder
import com.interview.takeawayslight.core.BasePresenter
import com.interview.takeawayslight.model.RestaurantViewModel

interface RestaurantListPresenter : BasePresenter<RestaurantListMVP> {
    fun init()
    fun sortingOrderSelected(sortingOrder: SortingOrder, selectedIndex: Int)
    fun restaurantClicked(restaurant: RestaurantViewModel)
    fun favoriteClicked(restaurant: RestaurantViewModel)
}