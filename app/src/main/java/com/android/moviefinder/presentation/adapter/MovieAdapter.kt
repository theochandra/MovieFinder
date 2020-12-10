package com.android.moviefinder.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.model.Movie
import com.android.moviefinder.databinding.ItemLoadingBinding
import com.android.moviefinder.databinding.ItemMovieBinding

class MovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_MOVIE = 1
    }

    private val movieList = ArrayList<Movie>()

    fun setList(movies: List<Movie>) {
//        movieList.clear()
        movieList.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MOVIE -> MovieViewHolder(ItemMovieBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            VIEW_TYPE_LOADING -> LoadingViewHolder(ItemLoadingBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            else -> MovieViewHolder(ItemMovieBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
        }
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
        return if (getItem(position) == null) VIEW_TYPE_LOADING else VIEW_TYPE_MOVIE
    }

    override fun getItemCount(): Int = movieList.size

    class MovieViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class LoadingViewHolder(
        binding: ItemLoadingBinding
    ) : RecyclerView.ViewHolder(binding.root)

}