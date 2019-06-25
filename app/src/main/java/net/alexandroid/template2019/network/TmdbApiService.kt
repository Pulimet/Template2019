package net.alexandroid.template2019.network

import kotlinx.coroutines.Deferred
import net.alexandroid.template2019.model.Tmdb
import retrofit2.http.GET

interface TmdbApiService{
    @GET("3/discover/movie?api_key=26596c147eacb8c3b84e0217b23ed41a")
    fun discoverAsync() : Deferred<Tmdb.Discover>
}