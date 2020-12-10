package com.android.moviefinder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.domain.usecase.GetMovieListByQueryUseCase
import com.android.moviefinder.presentation.mapper.MovieVMMapper

class MovieViewModelFactory(
    private val getMovieListByQueryUseCase: GetMovieListByQueryUseCase,
    private val movieVMMapper: MovieVMMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(getMovieListByQueryUseCase, movieVMMapper) as T
    }

}