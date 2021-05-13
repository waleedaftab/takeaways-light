package com.interview.takeawayslight.restaurantlist

import com.interview.data.mapper.Mapper
import com.interview.domain.interactors.RestaurantListInteractor
import com.interview.domain.interactors.SortingOrder
import com.interview.domain.model.Restaurant
import com.interview.takeawayslight.core.PresenterViewRefHolder
import com.interview.takeawayslight.core.SchedulersFactory
import com.interview.takeawayslight.model.RestaurantViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

class RestaurantListPresenterImpl(
    private val schedulersFactory: SchedulersFactory,
    private val restaurantListInteractor: RestaurantListInteractor,
    private val restaurantToRestaurantViewModelMapper: Mapper<Restaurant, RestaurantViewModel>
) : PresenterViewRefHolder<RestaurantListMVP>(), RestaurantListPresenter {
    private val disposables = CompositeDisposable()
    private var selectedSortingOrder = SortingOrder.BEST_MATCH
    private val restaurantViewModels = mutableListOf<RestaurantViewModel>()
    private val favorites = mutableListOf<String>()
    private var selectedSortingIndex: Int = 0

    override fun init() {
        loadSortOptions()
        loadRestaurants()
        loadFavorites()
    }

    override fun sortingOrderSelected(sortingOrder: SortingOrder, selectedIndex: Int) {
        selectedSortingOrder = sortingOrder
        selectedSortingIndex = selectedIndex
        loadSortOptions()
        loadRestaurants()
    }

    override fun restaurantClicked(restaurant: RestaurantViewModel) {
        ifViewAttachedThen {
            goToRestaurantDetailsActivity(restaurant.restaurantName)
        }
    }

    override fun favoriteClicked(restaurant: RestaurantViewModel) {
        if (restaurant.isFavorite) {
            removeFavorite(restaurant)
        } else {
            addFavorite(restaurant)
        }
    }

    override fun detachView() {
        super.detachView()
        disposables.clear()
    }

    private fun loadSortOptions() {
        ifViewAttachedThen {
            showSortingOptions(restaurantListInteractor.getSortOptions(), selectedSortingIndex)
        }
    }

    private fun syncFavorites() {
        ifViewAttachedThen {
            showRestaurants(restaurantViewModels.map {
                it.copy(isFavorite = favorites.contains(it.restaurantName))
            })
        }
    }

    private fun addFavorite(restaurant: RestaurantViewModel) {
        favorites.add(restaurant.restaurantName)
        syncFavorites()
        restaurantListInteractor.addFavorite(restaurant.restaurantName)
            .subscribeOn(schedulersFactory.io())
            .observeOn(schedulersFactory.main())
            .subscribe({}, {
                ifViewAttachedThen {
                    showError(it.message ?: "")
                }
            })
    }

    private fun removeFavorite(restaurant: RestaurantViewModel) {
        favorites.remove(restaurant.restaurantName)
        syncFavorites()
        restaurantListInteractor.removeFavorite(restaurant.restaurantName)
            .subscribeOn(schedulersFactory.io())
            .observeOn(schedulersFactory.main())
            .subscribe({}, {
                ifViewAttachedThen {
                    showError(it.message ?: "")
                }
            })
    }


    private fun loadRestaurants() {
        disposables.add(
            restaurantListInteractor.getRestaurantList(selectedSortingOrder)
                .map { it.map(restaurantToRestaurantViewModelMapper::map) }
                .subscribeOn(schedulersFactory.io())
                .observeOn(schedulersFactory.main())
                .subscribe({
                    restaurantViewModels.clear()
                    restaurantViewModels.addAll(it)
                    ifViewAttachedThen {
                        showRestaurants(restaurantViewModels)
                    }
                }, {
                    ifViewAttachedThen {
                        showError(it.message ?: "")
                    }
                })
        )
    }

    private fun loadFavorites() {
        disposables.add(
            restaurantListInteractor.getFavorites()
                .subscribeOn(schedulersFactory.io())
                .observeOn(schedulersFactory.main())
                .subscribe({
                    favorites.clear()
                    favorites.addAll(it)
                }, {
                    ifViewAttachedThen {
                        showError(it.message ?: "")
                    }
                })
        )
    }
}