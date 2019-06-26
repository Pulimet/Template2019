package net.alexandroid.template2019.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import net.alexandroid.template2019.TMDB_IMG_URL
import java.text.SimpleDateFormat
import java.util.*

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
    ) : Parcelable {
        fun getImageUrl() =
            when {
                posterImg != null -> TMDB_IMG_URL + posterImg
                backImg != null -> TMDB_IMG_URL + backImg
                else -> null
            }

        fun getYear(): String {
            val parsedDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(date)
            val formatOut = SimpleDateFormat("yyyy", Locale.US)
            return if (parsedDate != null) {
                formatOut.format(parsedDate)
            } else {
                ""
            }
        }

        fun getTitleWithYear() = title + " (" + getYear() + ")"

    }
}