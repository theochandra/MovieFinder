package com.android.domain.repository

import com.android.domain.model.MovieList
import com.android.domain.Result

interface MovieRepository {

    suspend fun getMovieListByQuery(searchKeywords: String, page: Int): Result<MovieList>

}