package com.android.moviefinder.presentation

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.Result
import com.android.domain.model.Movie
import com.android.domain.usecase.GetMovieListByQueryUseCase
import com.android.moviefinder.BuildConfig
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovieListByQueryUseCase: GetMovieListByQueryUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    val isLoading = ObservableBoolean()

    fun getMovieListByQuery() {
        isLoading.set(true)
        viewModelScope.launch {
            val result = getMovieListByQueryUseCase.execute(
                    BuildConfig.API_KEY,
                    searchKeywords = "trans",
                    page = 1
            )

            when (result) {
                is Result.Success -> _movies.postValue(result.data.results)
                is Result.Error -> _error.postValue(result.errorMessage)
                is Result.Exception -> _exception.postValue(result.exception)
            }

            isLoading.set(false)
        }
    }

}