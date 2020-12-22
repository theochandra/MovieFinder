package com.android.moviefinder.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.moviefinder.R
import com.android.moviefinder.base.BaseActivity
import com.android.moviefinder.databinding.ActivityMovieBinding
import com.android.moviefinder.di.Injector
import com.android.moviefinder.presentation.adapter.MovieAdapter
import com.android.moviefinder.presentation.listener.InfiniteScrollListener
import kotlinx.coroutines.*
import javax.inject.Inject

class MovieActivity : BaseActivity() {

    @Inject
    lateinit var factory: MovieViewModelFactory

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var infiniteScrollListener: InfiniteScrollListener

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        (application as Injector).createMovieSubComponent()
            .inject(this)
        movieViewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)

        binding.viewModel = movieViewModel

        initRecycleView()
        observeInput()
        observeData()
        observeError()
        observeException()
        observeIsStillLoading()
    }

    private fun initRecycleView() {
        movieAdapter = MovieAdapter()
        infiniteScrollListener = InfiniteScrollListener {
            movieAdapter.addLoadingItem() // add item loading before get data
            getData()
        }
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(this@MovieActivity)
            adapter = movieAdapter
            addOnScrollListener(
                    infiniteScrollListener
            )
        }
    }

    private fun getData() {
        movieViewModel.getMovieList()
    }

    private fun getData(searchKeywords: String) {
        movieViewModel.getMovieList(searchKeywords)
    }

    private fun observeInput() {
        // todo -> it always called when change orientation, make it don't!!
        /**
         * observe always called because observer re-attached when onCreate called
         * this is intended because lifecycle aware component
         */
        movieViewModel.getInput().observe(this, { input ->
            Log.i("MyTag", input)

            /**
             * 1. clear job
             * 2. clear list on adapter
             * 3. clear loading bar
             * 4. re-init current page and total page (viewModel)
             * 5. re-init total page (infinite scroll listener)
             */
            searchJob?.cancel()
            movieAdapter.clearList()
            movieViewModel.changeLoadingState(false)
            movieViewModel.initQueryParam()
            infiniteScrollListener.previousTotal = 0

            if (input.isNullOrEmpty()) return@observe
            if (input.length < 3) return@observe
            searchJob = coroutineScope.launch {
                delay(500)
                getData(input)
            }
        })
    }

    private fun observeData() {
        movieViewModel.itemList.observe(this, {
            movieAdapter.removeLoadingItem() // remove item loading before add data to list
            movieAdapter.setItemList(it)
        })
    }

    private fun observeIsStillLoading() {
        movieViewModel.isStillLoading.observe(this, {
            infiniteScrollListener.isLoading = it
        })
    }

    private fun observeError() {
        movieViewModel.error.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun observeException() {
        movieViewModel.exception.observe(this, {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })
    }

}