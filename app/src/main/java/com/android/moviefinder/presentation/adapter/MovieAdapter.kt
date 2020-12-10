package com.android.moviefinder.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.model.Movie
import com.android.moviefinder.databinding.ItemLoadingBinding
import com.android.moviefinder.databinding.ItemMovieBinding
import com.android.moviefinder.presentation.vm.ItemVM

class MovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_MOVIE = 1
    }

    private val movieList = ArrayList<Movie>()
    private val itemList = ArrayList<ItemVM>()

    fun setList(movies: List<Movie>) {
//        movieList.clear()
        movieList.addAll(movies)
    }

    fun setItemList(items: List<ItemVM>) {
        itemList.addAll(items)
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
                holder.binding.movie = movieList[position]
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