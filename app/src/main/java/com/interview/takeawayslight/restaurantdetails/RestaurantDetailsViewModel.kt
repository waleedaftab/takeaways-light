package com.interview.takeawayslight.restaurantdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.data.mapper.Mapper
import com.interview.domain.interactors.RestaurantDetailInteractor
import com.interview.domain.model.Restaurant
import com.interview.takeawayslight.model.RestaurantDetailsDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class RestaurantDetailsViewModel @Inject constructor(
    private val restaurantDetailInteractor: RestaurantDetailInteractor,
    private val mapper: Mapper<Restaurant, RestaurantDetailsDataModel>
) : ViewModel() {

    private val restaurantDetailsLiveData: MutableLiveData<RestaurantDetailsDataModel> =
        MutableLiveData()

    fun getRestaurantDetails(restaurantName: String): LiveData<RestaurantDetailsDataModel> =
        restaurantDetailsLiveData.also { loadRestaurantDetails(restaurantName) }

    private fun loadRestaurantDetails(restaurantName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantDetailInteractor.getRestaurantDetails(restaurantName)
                .map { mapper.map(it) }
                .collect { restaurantDetailsLiveData.postValue(it) }
        }
    }
}