package com.interview.takeawayslight.restaurantlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.data.mapper.Mapper
import com.interview.domain.interactors.RestaurantListInteractor
import com.interview.domain.interactors.SortingOrder
import com.interview.domain.model.Restaurant
import com.interview.takeawayslight.model.RestaurantDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class RestaurantListViewModel @Inject constructor(
    private val restaurantListInteractor: RestaurantListInteractor,
    private val mapper: Mapper<Restaurant, RestaurantDataModel>
) : ViewModel() {
    private val restaurantLiveData: MutableLiveData<List<RestaurantDataModel>> = MutableLiveData()
    private val sortingListLiveData: MutableLiveData<Int> = MutableLiveData()
    private var selectedSortingOrder = SortingOrder.BEST_MATCH
    private var selectedSortingIndex: Int = 0

    val getRestaurantList: LiveData<List<RestaurantDataModel>> = restaurantLiveData
    val getSelectedSortingOrder: LiveData<Int> = sortingListLiveData

    init {
        fetchRestaurantsList(selectedSortingOrder)
        sortingListLiveData.postValue(selectedSortingIndex)
    }

    fun addToFavorites(restaurantDataModel: RestaurantDataModel) {
        viewModelScope.launch {
            if (restaurantDataModel.isFavorite) {
                restaurantListInteractor.removeFavorite(restaurantDataModel.restaurantName)
            } else {
                restaurantListInteractor.addFavorite(restaurantDataModel.restaurantName)
            }
        }.invokeOnCompletion { fetchRestaurantsList(selectedSortingOrder) }
    }

    private fun fetchRestaurantsList(sortingOrder: SortingOrder) {
        selectedSortingOrder = sortingOrder
        viewModelScope.launch(Dispatchers.IO) {
            restaurantListInteractor.getRestaurantList(sortingOrder)
                .map { it.map(mapper::map) }
                .collect { restaurantLiveData.postValue(it) }
        }
    }

    fun sortingOrderSelected(sortingOrder: SortingOrder, selectedIndex: Int) {
        sortingListLiveData.postValue(selectedIndex)
        fetchRestaurantsList(sortingOrder)
    }

    fun getSortingOptions(): List<SortingOrder> = restaurantListInteractor.getSortOptions()


}
