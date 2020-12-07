package com.android.moviefinder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.domain.usecase.GetMovieListByQueryUseCase

class MovieViewModelFactory(
    private val getMovieListByQueryUseCase: GetMovieListByQueryUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(getMovieListByQueryUseCase) as T
    }

}