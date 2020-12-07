package com.android.moviefinder.di.movie

import com.android.domain.usecase.GetMovieListByQueryUseCase
import com.android.moviefinder.presentation.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMovieListByQueryUseCase: GetMovieListByQueryUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(getMovieListByQueryUseCase)
    }

}