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
import javax.inject.Inject

class MovieActivity : BaseActivity() {

    @Inject
    lateinit var factory: MovieViewModelFactory

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        (application as Injector).createMovieSubComponent()
            .inject(this)
        movieViewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)

        binding.viewModel = movieViewModel

        initRecycleView()
        observeData()
        observeError()
        observeException()
    }

    private fun initRecycleView() {
        movieAdapter = MovieAdapter()

        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(this@MovieActivity)
            adapter = movieAdapter
        }
    }

    private fun observeData() {
        movieViewModel.getMovieListByQuery()
        movieViewModel.movies.observe(this, {
            movieAdapter.setList(it)
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