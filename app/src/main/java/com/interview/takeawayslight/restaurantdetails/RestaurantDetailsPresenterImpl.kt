package com.interview.takeawayslight.restaurantdetails

import com.interview.data.mapper.Mapper
import com.interview.domain.interactors.RestaurantDetailInteractor
import com.interview.domain.model.Restaurant
import com.interview.takeawayslight.model.RestaurantDetailsDataModel

class RestaurantDetailsPresenterImpl(
    private val restaurantDetailInteractor: RestaurantDetailInteractor,
    private val restaurantToRestaurantDetailsMapper: Mapper<Restaurant, RestaurantDetailsDataModel>
) {
    fun init(restaurantName: String) {
        /* compositeDisposable.add(
             restaurantDetailInteractor.getRestaurantDetails(restaurantName)
                 .map(restaurantToRestaurantDetailsMapper::map)
                 .subscribeOn(schedulersFactory.io())
                 .observeOn(schedulersFactory.main())
                 .subscribe({
                     ifViewAttachedThen {
                         showRestaurantDetails(it)
                     }
                 }, {

                 })
         )*/
    }
}