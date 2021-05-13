package com.interview.takeawayslight.restaurantdetails

import com.interview.data.mapper.Mapper
import com.interview.domain.interactors.RestaurantDetailInteractor
import com.interview.domain.model.Restaurant
import com.interview.takeawayslight.core.PresenterViewRefHolder
import com.interview.takeawayslight.core.SchedulersFactory
import com.interview.takeawayslight.model.RestaurantDetailsViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

class RestaurantDetailsPresenterImpl(
    private val restaurantDetailInteractor: RestaurantDetailInteractor,
    private val schedulersFactory: SchedulersFactory,
    private val restaurantToRestaurantDetailsMapper: Mapper<Restaurant, RestaurantDetailsViewModel>
) : PresenterViewRefHolder<RestaurantDetailsMVP>(), RestaurantDetailsPresenter {
    private val compositeDisposable = CompositeDisposable()
    override fun init(restaurantName: String) {
        compositeDisposable.add(
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
        )
    }
}