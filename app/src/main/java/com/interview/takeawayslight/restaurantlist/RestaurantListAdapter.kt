package com.interview.takeawayslight.restaurantlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.interview.takeawayslight.R
import com.interview.takeawayslight.databinding.RestaurantCardBinding
import com.interview.takeawayslight.model.RestaurantViewModel

class RestaurantListAdapter(
    private val layoutInflater: LayoutInflater,
    private val onRestaurantClicked: (restaurant: RestaurantViewModel) -> Unit,
    private val onFavoriteSelected: (restaurant: RestaurantViewModel) -> Unit
) : RecyclerView.Adapter<RestaurantListAdapter.RestaurantCardViewHolder>() {

    private var restaurantsList: List<RestaurantViewModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantCardViewHolder {
        val binding = RestaurantCardBinding.inflate(layoutInflater, parent, false)
        return RestaurantCardViewHolder(binding)
    }

    override fun getItemCount() = restaurantsList.size

    override fun onBindViewHolder(holder: RestaurantCardViewHolder, position: Int) {
        with(restaurantsList[position]) {
            holder.binding.restaurantName.text = this.restaurantName
            holder.binding.restaurantStatus.text = this.status
            if (this.isFavorite) {
                holder.binding.favoriteIv.setImageResource(R.drawable.ic_favorite_selected)
            } else {
                holder.binding.favoriteIv.setImageResource(R.drawable.ic_favorite_unselected)
            }
            holder.binding.root.setOnClickListener { onRestaurantClicked(this) }
            holder.binding.favoriteIv.setOnClickListener { onFavoriteSelected(this) }
        }
    }

    fun setData(newList: List<RestaurantViewModel>) {
        this.restaurantsList = newList
        notifyDataSetChanged()
    }

    inner class RestaurantCardViewHolder(val binding: RestaurantCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}