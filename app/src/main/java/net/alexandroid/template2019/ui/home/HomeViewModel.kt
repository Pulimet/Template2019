package net.alexandroid.template2019.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.ui.base.BaseViewModel
import net.alexandroid.template2019.utils.SingleLiveEvent

class HomeViewModel : BaseViewModel() {

    var isAnimated = false

    private lateinit var openMovie: SingleLiveEvent<Tmdb.Movie>
    private lateinit var openFavorites: SingleLiveEvent<Unit>
    private lateinit var favoriteMovies: MutableLiveData<Tmdb.Discover>

    fun getOpenMovie(): LiveData<Tmdb.Movie> {
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

    fun getFavoriteMovies(): LiveData<Tmdb.Discover> {
        if (!::favoriteMovies.isInitialized) {
            favoriteMovies = MutableLiveData()
            // TODO Get movies from DB
        }
        return favoriteMovies
    }


    fun onMovieSelected(movie: Tmdb.Movie) {
        openMovie.postValue(movie)
    }

    fun onFabClick() {
        openFavorites.call()
    }
}