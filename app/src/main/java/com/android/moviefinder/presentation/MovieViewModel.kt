package com.android.moviefinder.presentation

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.Result
import com.android.domain.usecase.GetMovieListByQueryUseCase
import com.android.moviefinder.BuildConfig
import com.android.moviefinder.presentation.mapper.MovieVMMapper
import com.android.moviefinder.presentation.vm.ItemVM
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovieListByQueryUseCase: GetMovieListByQueryUseCase,
    private val movieVMMapper: MovieVMMapper
) : ViewModel() {

    private val _itemList = MutableLiveData<List<ItemVM>>()
    val itemList: LiveData<List<ItemVM>>
        get() = _itemList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    private val _isStillLoading = MutableLiveData<Boolean>()
    val isStillLoading: LiveData<Boolean>
        get() =  _isStillLoading

    val isLoading = ObservableBoolean()

    private var searchKeywords = ""
    private var currentPage = 1
    private var totalPage = 0

    fun initQueryParam() {
        currentPage = 1
        totalPage = 0
    }

    fun getMovieList(searchKeywords: String) {
        this.searchKeywords = searchKeywords
        getData(searchKeywords)
    }

    fun getMovieList() {
        getData(searchKeywords)
    }

    private fun getData(searchKeywords: String) {
        if (totalPage == currentPage) return
        if (totalPage == 0) {
            changeLoadingState(state = true)
            executeGetMovieListByQuery(searchKeywords)
        } else {
            if (currentPage < totalPage) {
                currentPage++
                executeGetMovieListByQuery(searchKeywords)
            }
        }
    }

    private fun executeGetMovieListByQuery(searchKeywords: String) {
        viewModelScope.launch {
            val result = getMovieListByQueryUseCase.execute(
                    BuildConfig.API_KEY,
                    searchKeywords,
                    currentPage
            )

            when (result) {
                is Result.Success -> {
                    _itemList.postValue(result.data.results.map {
                        movieVMMapper.map(it)
                    })
                    totalPage = result.data.totalPages
                }
                is Result.Error -> _error.postValue(result.errorMessage)
                is Result.Exception -> _exception.postValue(result.exception)
            }

            changeLoadingState(state = false)
            _isStillLoading.postValue(false)
        }
    }

    fun changeLoadingState(state: Boolean) {
        isLoading.set(state)
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.postValue(this.value)
    }

}