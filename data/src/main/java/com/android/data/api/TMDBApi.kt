package com.android.data.api

import com.android.data.response.MovieListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {

    @GET("movies/popular")
    fun getMovieListByQuery(
        @Query("keywords") searchKeywords: String,
        @Query("page") page:Int
    ): Deferred<Response<MovieListResponse>>

}