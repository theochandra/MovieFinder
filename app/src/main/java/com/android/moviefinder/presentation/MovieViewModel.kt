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

    private val _itemList = MutableLiveData<List<ItemVM>>()
    val itemList: LiveData<List<ItemVM>>
        get() = _itemList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    val isLoading = ObservableBoolean()

    private val items = ArrayList<ItemVM>()

    fun getMovieListByQuery() {
        isLoading.set(true)
        viewModelScope.launch {
            val result = getMovieListByQueryUseCase.execute(
                    BuildConfig.API_KEY,
                    searchKeywords = "trans",
                    page = 1
            )

            when (result) {
                is Result.Success -> {
                    items.addAll(result.data.results.map {
                        movieVMMapper.map(it)
                    })
                    _itemList.postValue(items)
                }
                is Result.Error -> _error.postValue(result.errorMessage)
                is Result.Exception -> _exception.postValue(result.exception)
            }

            isLoading.set(false)
        }
    }

    fun loadMore() {
//        items.clear()
//        items.add(ItemLoadingVM(true))
//        _itemList.postValue(items)

        viewModelScope.launch {
            val result = getMovieListByQueryUseCase.execute(
                    BuildConfig.API_KEY,
                    searchKeywords = "trans",
                    page = 2
            )

            when (result) {
                is Result.Success -> {
                    items.clear()
                    items.addAll(result.data.results.map {
                        movieVMMapper.map(it)
                    })
                    _itemList.postValue(items)
                }
                is Result.Error -> _error.postValue(result.errorMessage)
                is Result.Exception -> _exception.postValue(result.exception)
            }
        }
    }

}