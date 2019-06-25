package net.alexandroid.template2019.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

object Tmdb {
    data class Discover(
        val page: Int,
        @SerializedName("total_results") val totalResults: Int,
        @SerializedName("total_pages") val totalPages: Int,
        val results: List<Movie>
    )

    @Parcelize
    data class Movie(
        val id: Int,
        val title: String,
        @SerializedName("poster_path") val posterImg: String?,
        @SerializedName("backdrop_path") val backImg: String?,
        val overview: String,
        @SerializedName("release_date") val date: String, //2019-06-19
        @SerializedName("vote_average") val vote: Double
    ) : Parcelable
}