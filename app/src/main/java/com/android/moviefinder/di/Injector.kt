package com.android.moviefinder.di

import com.android.moviefinder.di.movie.MovieSubComponent

interface Injector {

    fun createMovieSubComponent(): MovieSubComponent

}