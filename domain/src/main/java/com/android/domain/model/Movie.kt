package com.android.domain.model

data class Movie(
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val title: String
)