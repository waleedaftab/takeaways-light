package com.interview.takeawayslight.restaurantdetails

import android.os.Bundle
import com.interview.takeawayslight.R
import com.interview.takeawayslight.core.BaseActivity
import com.interview.takeawayslight.databinding.ActivityRestaurantDetailsBinding
import com.interview.takeawayslight.model.RestaurantDetailsViewModel
import com.interview.takeawayslight.router.IntentExtras.RESTAURANT_NAME
import javax.inject.Inject

class RestaurantDetailsActivity : BaseActivity<RestaurantDetailsMVP, RestaurantDetailsPresenter>(), RestaurantDetailsMVP {

    @Inject
    override lateinit var presenter: RestaurantDetailsPresenter

    private lateinit var binding: ActivityRestaurantDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.restaurant_details)
        }
    }

    override fun onStart() {
        super.onStart()
        intent.getStringExtra(RESTAURANT_NAME)?.let {
            presenter.init(it)
        }
    }

    override fun showRestaurantDetails(viewModel: RestaurantDetailsViewModel) {
        binding.restaurantName.text = viewModel.name
        binding.restaurantStatus.text = viewModel.status
        binding.favoriteIv.setImageResource(
            if (viewModel.isFavorite) R.drawable.ic_favorite_selected else R.drawable.ic_favorite_unselected
        )
        binding.bestMatch.text = viewModel.bestMatch.toString()
        binding.newest.text = viewModel.newest.toString()
        binding.averageRating.text = viewModel.ratingAverage.toString()
        binding.distance.text = viewModel.distance.toString()
        binding.popularity.text = viewModel.popularity.toString()
        binding.productPrice.text = viewModel.averageProductPrice.toString()
        binding.deliveryCost.text = viewModel.deliveryCosts.toString()
        binding.minCost.text = viewModel.minCost.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}