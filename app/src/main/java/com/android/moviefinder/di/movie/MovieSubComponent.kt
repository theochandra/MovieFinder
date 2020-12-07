package com.android.moviefinder.di.movie

import com.android.moviefinder.presentation.MovieActivity
import dagger.Subcomponent

@MovieScope
@Subcomponent (modules = [MovieModule::class])
interface MovieSubComponent {

    fun inject(movieActivity: MovieActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieSubComponent
    }

}