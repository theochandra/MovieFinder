package com.android.moviefinder.presentation.typesfactory

import android.view.View
import com.android.domain.model.Movie
import com.android.moviefinder.presentation.holder.BaseViewHolder

interface TypesFactory {

    fun type(movie: Movie): Int

    fun holder(type: Int, view: View): BaseViewHolder<*>

}