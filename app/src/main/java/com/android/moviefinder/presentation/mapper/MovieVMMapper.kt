package com.android.moviefinder.presentation.mapper

import com.android.domain.model.Movie
import com.android.moviefinder.presentation.vm.ItemMovieVM
import javax.inject.Inject

class MovieVMMapper @Inject constructor() {

    fun map(movie: Movie): ItemMovieVM {
        return ItemMovieVM(
                movie.id,
                movie.overview,
                movie.popularity,
                movie.posterPath,
                movie.releaseDate,
                movie.title
        )
    }

}