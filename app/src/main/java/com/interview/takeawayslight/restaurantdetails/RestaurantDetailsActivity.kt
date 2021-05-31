package com.interview.takeawayslight.restaurantdetails

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.interview.takeawayslight.R
import com.interview.takeawayslight.databinding.ActivityRestaurantDetailsBinding
import com.interview.takeawayslight.model.RestaurantDetailsDataModel
import com.interview.takeawayslight.router.IntentExtras.RESTAURANT_NAME
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RestaurantDetailsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityRestaurantDetailsBinding

    private val restaurantDetailsViewModel: RestaurantDetailsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.restaurant_details)
        }
        intent.getStringExtra(RESTAURANT_NAME)?.let {
            restaurantDetailsViewModel.getRestaurantDetails(it)
                .observe(this, this::showRestaurantDetails)
        }
    }

    private fun showRestaurantDetails(dataModel: RestaurantDetailsDataModel) {
        binding.restaurantName.text = dataModel.name
        binding.restaurantStatus.text = dataModel.status
        binding.favoriteIv.setImageResource(
            if (dataModel.isFavorite) R.drawable.ic_favorite_selected else R.drawable.ic_favorite_unselected
        )
        binding.bestMatch.text = dataModel.bestMatch.toString()
        binding.newest.text = dataModel.newest.toString()
        binding.averageRating.text = dataModel.ratingAverage.toString()
        binding.distance.text = dataModel.distance.toString()
        binding.popularity.text = dataModel.popularity.toString()
        binding.productPrice.text = dataModel.averageProductPrice.toString()
        binding.deliveryCost.text = dataModel.deliveryCosts.toString()
        binding.minCost.text = dataModel.minCost.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}