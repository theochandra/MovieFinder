package com.android.data.api

import com.android.data.response.MovieListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceEndPoint {

    @GET("movies/popular")
    suspend fun getMovieListByQuery(
        @Query("keywords") searchKeywords: String,
        @Query("page") page:Int
    ): Response<MovieListResponse>

}