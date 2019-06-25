package net.alexandroid.template2019.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel() {

    private lateinit var openMovie: MutableLiveData<Tmdb.Movie>

    fun getOpenMovie(): LiveData<Tmdb.Movie> {
        if (!::openMovie.isInitialized) {
            openMovie = MutableLiveData()
        }
        return openMovie
    }

    fun onMovieSelected(movie: Tmdb.Movie) {
        openMovie.postValue(movie)
    }
}