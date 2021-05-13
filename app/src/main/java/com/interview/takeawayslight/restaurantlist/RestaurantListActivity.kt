package com.interview.takeawayslight.restaurantlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.interview.domain.interactors.SortingOrder
import com.interview.takeawayslight.core.BaseActivity
import com.interview.takeawayslight.databinding.ActivityRestaurantListBinding
import com.interview.takeawayslight.model.RestaurantViewModel
import com.interview.takeawayslight.restaurantdetails.RestaurantDetailsActivity
import com.interview.takeawayslight.router.IntentExtras
import javax.inject.Inject

class RestaurantListActivity : BaseActivity<RestaurantListMVP, RestaurantListPresenter>(),
    RestaurantListMVP {

    @Inject
    override lateinit var presenter: RestaurantListPresenter

    @Inject
    lateinit var restaurantListAdapter: RestaurantListAdapter

    @Inject
    lateinit var sortingListAdapter: SortingListAdapter

    private lateinit var binding: ActivityRestaurantListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    override fun onStart() {
        super.onStart()
        presenter.init()
    }

    private fun setupViews() {
        binding.restaurantsRecyclerView.adapter = restaurantListAdapter
        binding.sortingRecyclerView.adapter = sortingListAdapter
    }

    override fun showRestaurants(restaurants: List<RestaurantViewModel>) {
        restaurantListAdapter.setData(restaurants)
    }

    override fun showSortingOptions(sortingOptions: List<SortingOrder>, selectedIndex: Int) {
        sortingListAdapter.setData(sortingOptions, selectedIndex)
    }

    override fun showError(errorMessage: String) {
        Log.d("error", errorMessage)
    }

    override fun goToRestaurantDetailsActivity(restaurantName: String) {
        val intent = Intent(this, RestaurantDetailsActivity::class.java).apply {
            putExtra(IntentExtras.RESTAURANT_NAME, restaurantName)
        }
        startActivity(intent)
    }

}