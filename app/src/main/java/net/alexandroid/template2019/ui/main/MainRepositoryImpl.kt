package net.alexandroid.template2019.ui.main

import kotlinx.coroutines.Deferred
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.network.TmdbApiService

class MainRepositoryImpl(private val tmdbApiService: TmdbApiService) : MainRepository {
    override fun discoverMoviesAsync(): Deferred<Tmdb.Discover> = tmdbApiService.discoverAsync()
}