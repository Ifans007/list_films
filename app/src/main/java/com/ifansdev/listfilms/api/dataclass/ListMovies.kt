package com.ifansdev.listfilms.api.dataclass

import com.google.gson.annotations.SerializedName

data class ListMovies(
        @SerializedName("page") val page: Int,
        @SerializedName("results") val results: List<Movie>
)