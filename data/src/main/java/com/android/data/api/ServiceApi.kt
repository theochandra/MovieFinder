package com.android.data.api

import com.android.data.response.MovieListResponse
import retrofit2.Response
import javax.inject.Inject

class ServiceApi @Inject constructor(
        private val serviceEndPoint: ServiceEndPoint
) {

    suspend fun getMovieListByQuery(
            searchKeywords: String,
            page:Int
    ): Response<MovieListResponse> {
        return serviceEndPoint.getMovieListByQuery(searchKeywords, page)
    }

}