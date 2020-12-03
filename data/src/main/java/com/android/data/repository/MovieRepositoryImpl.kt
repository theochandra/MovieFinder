package com.android.data.repository

import com.android.data.api.TMDBApi
import com.android.data.mapper.MovieMapper
import com.android.data.safeApiCall
import com.android.domain.Result
import com.android.domain.model.MovieList
import com.android.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor (
        private val tmdbApi: TMDBApi,
        private val movieMapper: MovieMapper
) : MovieRepository {

    override suspend fun getMovieListByQuery(
            searchKeywords: String, page: Int
    ): Result<MovieList> {
        return safeApiCall(
                call = { getMoviesFromApi(searchKeywords, page) },
                errorMessage = "Exception occurred!"
        )
    }

    private suspend fun getMoviesFromApi(
            searchKeywords: String, page: Int
    ): Result<MovieList> {
        val result = tmdbApi.getMovieListByQuery(searchKeywords, page).await()
        if (result.isSuccessful) {
            val body = result.body()
            if (body != null) {
                val movieList = movieMapper.map(
                    body
                )
                return Result.Success(movieList)
            }
        }
        return Result.Error(result.code(), result.message())
    }

}