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
import com.android.moviefinder.presentation.vm.ItemLoadingVM
import com.android.moviefinder.presentation.vm.ItemVM
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovieListByQueryUseCase: GetMovieListByQueryUseCase,
    private val movieVMMapper: MovieVMMapper
) : ViewModel() {

    private val _itemList = MutableLiveData<MutableList<ItemVM>>()
    val itemList: LiveData<MutableList<ItemVM>>
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

    private var currentPage = 1
    private var totalPage = 0

    init {
        _itemList.value = ArrayList()
    }

    fun getMovieList() {
        if (totalPage == currentPage) return
        if (totalPage == 0) {
            changeLoadingState(state = true)
            executeGetMovieListByQuery()
        } else {
            if (currentPage < totalPage) {
                currentPage++
                addLoadingItem()
                executeGetMovieListByQuery()
            }
        }
    }

    private fun executeGetMovieListByQuery() {
        viewModelScope.launch {
            val result = getMovieListByQueryUseCase.execute(
                    BuildConfig.API_KEY,
                    searchKeywords = "trans",
                    currentPage
            )

            when (result) {
                is Result.Success -> {
                    // remove loading item in last position if any
                    removeLoadingItem()
                    // add movie list
                    _itemList.value?.addAll(result.data.results.map {
                        movieVMMapper.map(it)
                    })
                    _itemList.notifyObserver()

                    totalPage = result.data.totalPages
                }
                is Result.Error -> _error.postValue(result.errorMessage)
                is Result.Exception -> _exception.postValue(result.exception)
            }

            changeLoadingState(state = false)
            _isStillLoading.postValue(false)
        }
    }

    private fun changeLoadingState(state: Boolean) {
        isLoading.set(state)
    }

    private fun addLoadingItem() {
        _itemList.value?.add(ItemLoadingVM(true))
        _itemList.notifyObserver()
    }

    private fun removeLoadingItem() {
        if (_itemList.value.isNullOrEmpty()) return
        if (_itemList.value?.last() == ItemLoadingVM(true)) {
            itemList.value?.removeLast()
        }
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

}