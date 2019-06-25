package net.alexandroid.template2019.ui.main

import kotlinx.coroutines.Deferred
import net.alexandroid.template2019.model.Tmdb

interface MainRepository {
    fun discoverMoviesAsync(): Deferred<Tmdb.Discover>
}