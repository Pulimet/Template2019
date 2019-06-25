package net.alexandroid.template2019.network

import kotlinx.coroutines.Deferred
import net.alexandroid.template2019.TMDB_API_KEY
import net.alexandroid.template2019.model.Tmdb
import retrofit2.http.GET

interface TmdbApiService{
    @GET("3/discover/movie?api_key=$TMDB_API_KEY")
    fun discoverAsync() : Deferred<Tmdb.Discover>
}