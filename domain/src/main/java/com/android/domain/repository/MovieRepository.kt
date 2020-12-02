package com.android.domain.repository

import com.android.domain.model.Movie

interface MovieRepository {

    suspend fun getMovieListByQuery(searchKeywords: String, page: Int): List<Movie>

}