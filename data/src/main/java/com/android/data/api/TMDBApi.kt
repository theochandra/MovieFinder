package com.android.data.api

import com.android.data.response.MovieListResponse
import retrofit2.Response
import javax.inject.Inject

class TMDBApi @Inject constructor(private val tmdbEndPoint: TMDBEndPoint) {

    suspend fun getMovieListByQuery(
            searchKeywords: String,
            page: Int
    ): Response<MovieListResponse> {
        return tmdbEndPoint.getMovieListByQuery(searchKeywords, page)
    }

}