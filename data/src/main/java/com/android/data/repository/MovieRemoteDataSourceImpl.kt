package com.android.data.repository

import com.android.data.api.TMDBApi
import com.android.data.response.MovieListResponse
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val tmdbApi: TMDBApi) : MovieRemoteDataSource {

    override suspend fun getMovieListByQuery(
            searchKeywords: String,
            page: Int
    ): Response<MovieListResponse> {
        return tmdbApi.getMovieListByQuery(searchKeywords, page)
    }

}