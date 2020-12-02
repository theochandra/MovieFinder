package com.android.data.repository

import com.android.data.response.MovieListResponse
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getMovieListByQuery(searchKeywords: String, page: Int): Response<MovieListResponse>

}