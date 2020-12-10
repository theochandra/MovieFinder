package com.android.moviefinder.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.moviefinder.R
import com.android.moviefinder.base.BaseActivity
import com.android.moviefinder.databinding.ActivityMovieBinding
import com.android.moviefinder.di.Injector
import com.android.moviefinder.presentation.adapter.MovieAdapter
import com.android.moviefinder.presentation.listener.InfiniteScrollListener
import javax.inject.Inject

class MovieActivity : BaseActivity() {

    @Inject
    lateinit var factory: MovieViewModelFactory

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var infiniteScrollListener: InfiniteScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        (application as Injector).createMovieSubComponent()
            .inject(this)
        movieViewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)

        binding.viewModel = movieViewModel

        initRecycleView()
        getData()
        observeData()
        observeError()
        observeException()
    }

    private fun initRecycleView() {
        movieAdapter = MovieAdapter()
        infiniteScrollListener = InfiniteScrollListener { movieViewModel.loadMore() }
        infiniteScrollListener.isLoading = false

        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(this@MovieActivity)
            adapter = movieAdapter
            addOnScrollListener(
                    InfiniteScrollListener { movieViewModel.loadMore() }
            )
        }
    }

    private fun getData() {
        movieViewModel.getMovieListByQuery()
    }

    private fun observeData() {
        movieViewModel.itemList.observe(this, {
            movieAdapter.setItemList(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun observeError() {
        movieViewModel.error.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun observeException() {
        movieViewModel.exception.observe(this, {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })
    }

}