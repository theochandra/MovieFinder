package com.android.moviefinder.presentation.vm

data class ItemMovieVM(
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String
) : ItemVM(VIEW_TYPE_MOVIE)