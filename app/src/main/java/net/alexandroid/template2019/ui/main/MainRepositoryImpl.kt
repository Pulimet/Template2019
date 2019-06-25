package net.alexandroid.template2019.ui.main

import kotlinx.coroutines.Deferred
import net.alexandroid.template2019.TMDB_API_KEY
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.network.TmdbApiService

class MainRepositoryImpl(private val tmdbApiService: TmdbApiService) : MainRepository {
    override fun discoverMoviesAsync(): Deferred<Tmdb.Discover> =
        tmdbApiService.discoverAsync(TMDB_API_KEY, "release_date.desc")
}