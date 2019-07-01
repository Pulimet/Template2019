package net.alexandroid.template2019.network

import kotlinx.coroutines.Deferred
import net.alexandroid.template2019.TMDB_API_KEY
import net.alexandroid.template2019.model.Tmdb
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {
    @GET("3/discover/movie")
    fun discoverAsync(
        @Query("api_key") apiKey: String = TMDB_API_KEY,
        @Query("page") page: Long = 1,
        @Query("sort_by") sortBy: String = "release_date.desc",
        @Query("release_date.lte") todayDate: String = "2019-06-25",
        @Query("vote_count.gte") minNumOfVotes : Int = 2
    ): Deferred<Tmdb.Discover>
}