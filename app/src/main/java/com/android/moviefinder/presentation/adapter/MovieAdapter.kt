package com.android.moviefinder.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.moviefinder.databinding.ItemLoadingBinding
import com.android.moviefinder.databinding.ItemMovieBinding
import com.android.moviefinder.presentation.vm.ItemLoadingVM
import com.android.moviefinder.presentation.vm.ItemMovieVM
import com.android.moviefinder.presentation.vm.ItemVM

class MovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemList = ArrayList<ItemVM>()

    fun setItemList(items: List<ItemVM>) {
//        itemList.clear()
        itemList.addAll(items)
        notifyItemInserted(itemList.size - 1)
    }

    fun addLoadingItem() {
        itemList.add(ItemLoadingVM(true))
        notifyItemInserted(itemList.size - 1)
    }

    fun removeLoadingItem() {
        if (itemList.isNullOrEmpty()) return
        if (itemList.last() == ItemLoadingVM(true))
            itemList.removeLast()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var viewHolder: RecyclerView.ViewHolder
        when (viewType) {
            ItemVM.VIEW_TYPE_LOADING -> viewHolder = LoadingViewHolder(
                ItemLoadingBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
            ItemVM.VIEW_TYPE_MOVIE -> viewHolder = MovieViewHolder(
                ItemMovieBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                val itemMovieVM = itemList[position] as ItemMovieVM
                holder.binding.movie = itemMovieVM
            }
            is LoadingViewHolder -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].getViewType()
    }

    override fun getItemCount(): Int = itemList.size

    class MovieViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class LoadingViewHolder(
        binding: ItemLoadingBinding
    ) : RecyclerView.ViewHolder(binding.root)

}