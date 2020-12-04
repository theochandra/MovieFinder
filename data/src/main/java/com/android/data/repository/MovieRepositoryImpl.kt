package com.android.data.repository

import com.android.data.api.ServiceApi
import com.android.data.mapper.MovieMapper
import com.android.data.safeApiCall
import com.android.domain.Result
import com.android.domain.model.MovieList
import com.android.domain.repository.MovieRepository

class MovieRepositoryImpl (
        private val serviceApi: ServiceApi,
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
        val result = serviceApi.getMovieListByQuery(searchKeywords, page)
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