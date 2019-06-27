package net.alexandroid.template2019.ui.home

import androidx.lifecycle.LiveData
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.ui.base.BaseViewModel
import net.alexandroid.template2019.utils.SingleLiveEvent

class HomeViewModel : BaseViewModel() {

    var isAnimated = false

    private lateinit var openMovie: SingleLiveEvent<Tmdb.Movie>

    fun getOpenMovie(): LiveData<Tmdb.Movie> {
        if (!::openMovie.isInitialized) {
            openMovie = SingleLiveEvent()
        }
        return openMovie
    }

    fun onMovieSelected(movie: Tmdb.Movie) {
        openMovie.postValue(movie)
    }
}