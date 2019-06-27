package net.alexandroid.template2019.model

import com.google.gson.annotations.SerializedName

object Tmdb {
    data class Discover(
        val page: Int,
        @SerializedName("total_results") val totalResults: Int,
        @SerializedName("total_pages") val totalPages: Int,
        val results: MutableList<Movie>
    )
}