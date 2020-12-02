package com.android.data.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (
        @SerializedName("id")
        val id: Int,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String
)