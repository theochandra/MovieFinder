package com.android.moviefinder.presentation.typesfactory

import android.view.View
import com.android.domain.model.Movie
import com.android.moviefinder.R
import com.android.moviefinder.presentation.holder.BaseViewHolder
import com.android.moviefinder.presentation.holder.MovieHolder

class TypesFactoryImpl : TypesFactory {

    override fun type(movie: Movie) = R.layout.item_movie

    override fun holder(type: Int, view: View): BaseViewHolder<*> {
        return when(type) {
            R.layout.item_movie -> MovieHolder(view)
            else -> throw RuntimeException("Illegal view type")
        }
    }
}