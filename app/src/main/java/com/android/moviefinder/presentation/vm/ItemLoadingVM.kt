package com.android.moviefinder.presentation.vm

data class ItemLoadingVM(
    val isVisible: Boolean
) : ItemVM(VIEW_TYPE_LOADING)