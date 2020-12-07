package com.android.domain.repository

import com.android.domain.model.MovieList
import com.android.domain.Result

interface MovieRepository {

    suspend fun getMovieListByQuery(apiKey: String, searchKeywords: String, page: Int): Result<MovieList>

}