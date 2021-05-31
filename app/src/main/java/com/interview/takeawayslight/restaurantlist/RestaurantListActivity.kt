package com.interview.takeawayslight.restaurantlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.interview.takeawayslight.databinding.ActivityRestaurantListBinding
import com.interview.takeawayslight.restaurantdetails.RestaurantDetailsActivity
import com.interview.takeawayslight.router.IntentExtras
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RestaurantListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var restaurantListAdapter: RestaurantListAdapter
    lateinit var sortingListAdapter: SortingListAdapter
    private lateinit var binding: ActivityRestaurantListBinding

    private val restaurantListViewModel: RestaurantListViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupSubscriptions()
    }

    private fun setupViews() {
        sortingListAdapter =
            SortingListAdapter(resources, layoutInflater) { sortOption, selectedIndex ->
                restaurantListViewModel.sortingOrderSelected(sortOption, selectedIndex)
            }
        restaurantListAdapter = RestaurantListAdapter(layoutInflater, {
            goToRestaurantDetailsActivity(it.restaurantName)
        }, {
            restaurantListViewModel.addToFavorites(it)
        })
        binding.restaurantsRecyclerView.adapter = restaurantListAdapter
        binding.sortingRecyclerView.adapter = sortingListAdapter
        sortingListAdapter.setData(restaurantListViewModel.getSortingOptions())
    }

    private fun setupSubscriptions() {
        restaurantListViewModel.getRestaurantList.observe(this, {
            restaurantListAdapter.setData(it)
        })
        restaurantListViewModel.getSelectedSortingOrder.observe(this) {
            sortingListAdapter.setSelectedIndex(it)
        }
    }

    private fun goToRestaurantDetailsActivity(restaurantName: String) {
        val intent = Intent(this, RestaurantDetailsActivity::class.java).apply {
            putExtra(IntentExtras.RESTAURANT_NAME, restaurantName)
        }
        startActivity(intent)
    }
}