package net.alexandroid.template2019.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import net.alexandroid.template2019.TMDB_IMG_URL
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
@Entity(tableName = "movies")
data class Movie(
    var position: Int = -1,
    @PrimaryKey
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

    private fun getYear(): String {
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