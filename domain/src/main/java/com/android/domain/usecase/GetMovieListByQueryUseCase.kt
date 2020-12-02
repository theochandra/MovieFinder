package com.android.domain.usecase

import com.android.domain.model.Movie
import com.android.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieListByQueryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun execute(searchKeywords: String, page: Int): List<Movie> = movieRepository
        .getMovieListByQuery(searchKeywords, page)

}