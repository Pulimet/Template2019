package net.alexandroid.template2019.network

import kotlinx.coroutines.Deferred
import net.alexandroid.template2019.model.Tmdb
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {
    @GET("3/discover/movie")
    fun discoverAsync(
        @Query("api_key") apiKey: String,
        @Query("sort_by") sortBy: String
    ): Deferred<Tmdb.Discover>
}