package net.alexandroid.template2019.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.alexandroid.template2019.model.Movie
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.repos.MovieRepository
import net.alexandroid.template2019.ui.base.BaseViewModel
import net.alexandroid.template2019.utils.SingleLiveEvent

class HomeViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {

    var isAnimated = false

    private lateinit var openMovie: SingleLiveEvent<Movie>
    private lateinit var openFavorites: SingleLiveEvent<Unit>
    private lateinit var favoriteMovies: LiveData<List<Movie>>

    fun getOpenMovie(): LiveData<Movie> {
        if (!::openMovie.isInitialized) {
            openMovie = SingleLiveEvent()
        }
        return openMovie
    }

    fun getOpenFavorites(): LiveData<Unit> {
        if (!::openFavorites.isInitialized) {
            openFavorites = SingleLiveEvent()
        }
        return openFavorites
    }

    fun getFavoriteMovies(): LiveData<List<Movie>> {
        if (!::favoriteMovies.isInitialized) {
            favoriteMovies = movieRepository.allMovies
        }
        return favoriteMovies
    }


    fun onMovieSelected(movie: Movie) {
        openMovie.postValue(movie)
    }

    fun onFabClick() {
        openFavorites.call()
    }
}