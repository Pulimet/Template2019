package net.alexandroid.template2019.ui.main

import kotlinx.coroutines.Deferred
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.network.TmdbApiService
import java.text.SimpleDateFormat
import java.util.*

class MainRepositoryImpl(private val tmdbApiService: TmdbApiService) : MainRepository {
    override fun discoverMoviesAsync(): Deferred<Tmdb.Discover> =
        tmdbApiService.discoverAsync(todayDate = getTodayDate())

    private fun getTodayDate(): String = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
}