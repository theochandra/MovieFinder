package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.model.MovieList
import com.android.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieListByQueryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun execute(apiKey: String, searchKeywords: String, page: Int): Result<MovieList> {
        return movieRepository.getMovieListByQuery(apiKey, searchKeywords, page)
    }

}