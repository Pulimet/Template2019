package net.alexandroid.template2019.repos

import kotlinx.coroutines.Deferred
import net.alexandroid.template2019.model.Tmdb

interface MainRepository {
    fun discoverMoviesAsync(): Deferred<Tmdb.Discover>
}