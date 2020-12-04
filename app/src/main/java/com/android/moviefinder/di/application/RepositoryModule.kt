package com.android.moviefinder.di.application

import com.android.data.api.ServiceApi
import com.android.data.mapper.MovieMapper
import com.android.data.repository.MovieRepositoryImpl
import com.android.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
            serviceApi: ServiceApi,
            movieMapper: MovieMapper
    ): MovieRepository {
        return MovieRepositoryImpl(serviceApi, movieMapper)
    }

}