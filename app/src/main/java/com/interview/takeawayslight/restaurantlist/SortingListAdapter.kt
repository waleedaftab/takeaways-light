package com.interview.takeawayslight.restaurantlist

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.interview.domain.interactors.SortingOrder
import com.interview.takeawayslight.R
import com.interview.takeawayslight.databinding.SortCardBinding

class SortingListAdapter(
    private val resources: Resources,
    private val layoutInflater: LayoutInflater,
    private val onSortOptionClicked: (sortOption: SortingOrder, selectedIndex: Int) -> Unit
) : RecyclerView.Adapter<SortingListAdapter.SortOptionViewHolder>() {

    private var sortingOptionsList: List<SortingOrder> = emptyList()
    private var selectedIndex: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortOptionViewHolder {
        val binding = SortCardBinding.inflate(layoutInflater, parent, false)
        return SortOptionViewHolder(binding)
    }

    override fun getItemCount() = sortingOptionsList.size

    override fun onBindViewHolder(holder: SortOptionViewHolder, position: Int) {
        holder.binding.sortOptionName.text = sortingOptionsList[position].title
        holder.binding.sortCard.setCardBackgroundColor(
            resources.getColor(if (position == selectedIndex) (R.color.yellow) else R.color.white)
        )
        holder.binding.root.setOnClickListener {
            onSortOptionClicked(sortingOptionsList[position], position)
        }
    }

    fun setData(sortingOptions: List<SortingOrder>, selectedIndex: Int) {
        this.sortingOptionsList = sortingOptions
        this.selectedIndex = selectedIndex
        notifyDataSetChanged()
    }

    inner class SortOptionViewHolder(val binding: SortCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}