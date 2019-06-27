package net.alexandroid.template2019.repos

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import net.alexandroid.template2019.db.MovieDao
import net.alexandroid.template2019.model.Movie

class MovieRepository(private val movieDao: MovieDao) {

    val allMovies: LiveData<List<Movie>> = movieDao.getMovies()

    @WorkerThread
    suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }
}