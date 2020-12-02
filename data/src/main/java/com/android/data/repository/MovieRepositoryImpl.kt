package com.android.data.repository

import com.android.data.mapper.MovieMapper
import com.android.domain.model.Movie
import com.android.domain.repository.MovieRepository

class MovieRepositoryImpl (
        private val movieRemoteDataSource: MovieRemoteDataSource,
        private val movieMapper: MovieMapper
) : MovieRepository {

    override suspend fun getMovieListByQuery(searchKeywords: String, page: Int): List<Movie> {
        return getMoviesFromApi(searchKeywords, page)
    }

    private suspend fun getMoviesFromApi(searchKeywords: String, page: Int):List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            val response = movieRemoteDataSource.getMovieListByQuery(searchKeywords, page)
            val body = response.body()
            body?.let {
                movieList = it.results.map { movieMapper.map(it) }
            }
        } catch (exception: Exception) {

        }

        return movieList
    }

}