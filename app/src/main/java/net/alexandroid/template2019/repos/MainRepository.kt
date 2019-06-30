package net.alexandroid.template2019.repos

import kotlinx.coroutines.Deferred
import net.alexandroid.template2019.model.Tmdb

interface MainRepository {
    fun discoverMoviesAsync(page: Long = 1): Deferred<Tmdb.Discover>
}