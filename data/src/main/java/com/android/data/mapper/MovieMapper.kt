package com.android.data.mapper

import com.android.data.response.MovieResponse
import com.android.data.response.MovieListResponse
import com.android.domain.model.Movie
import com.android.domain.model.MovieList
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun map(response: MovieListResponse): MovieList {
        return MovieList(
                page = response.page,
                results = response.results.map { map(it) },
                totalPages = response.totalPages,
                totalResults = response.totalResults
        )
    }

    // todo -> need to fix if poster path is null
    private fun map(movie: MovieResponse): Movie {
        return Movie(
                id = movie.id,
                overview = movie.overview,
                popularity = movie.popularity,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate,
                title = movie.title
        )
    }

}