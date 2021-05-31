package com.interview.takeawayslight.mapper

import com.interview.domain.model.Restaurant
import com.interview.domain.model.SortingValues
import com.interview.takeawayslight.model.RestaurantDataModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RestaurantToRestaurantDataModelMapperImpl {

    private val mapper = RestaurantToRestaurantViewModelMapper()

    @Test
    fun testMap() {
        Assert.assertEquals(mapper.map(getRestaurant()), expectedRestaurantViewModel())
    }

    private fun expectedRestaurantViewModel() = RestaurantDataModel("restaurant", "open", true)

    private fun getRestaurant() = Restaurant(
        "restaurant",
        "open",
        SortingValues(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
    ).apply { isFavorite = true }
}