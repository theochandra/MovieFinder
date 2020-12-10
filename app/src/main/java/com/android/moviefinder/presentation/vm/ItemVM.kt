package com.android.moviefinder.presentation.vm

open class ItemVM(private var viewType: Int) {

    companion object {

        const val VIEW_TYPE_LOADING = 0

        const val VIEW_TYPE_MOVIE = 1

    }

    fun getViewType(): Int {
        return viewType
    }

    fun setViewType(viewType: Int) {
        this.viewType = viewType
    }

}