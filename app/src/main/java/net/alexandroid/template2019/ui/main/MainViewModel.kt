package net.alexandroid.template2019.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.alexandroid.template2019.model.Movie
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.network.retryIO
import net.alexandroid.template2019.repos.MainRepository
import net.alexandroid.template2019.ui.base.BaseViewModel
import okhttp3.internal.toImmutableList


const val EVENT_START_ANIMATION = 0

class MainViewModel(private val repo: MainRepository) : BaseViewModel() {

    private lateinit var viewListener: MutableLiveData<Int>
    private lateinit var discoverMovies: MutableLiveData<List<Movie>>

    private var isAppLaunchAnimated = false

    fun getViewEvents(): LiveData<Int> {
        if (!::viewListener.isInitialized) {
            viewListener = MutableLiveData()
        }
        return viewListener
    }

    fun getDiscoveredMovies(): LiveData<List<Movie>> {
        if (!::discoverMovies.isInitialized) {
            discoverMovies = MutableLiveData()
            fetchDiscoveredMovies()
        }
        return discoverMovies
    }

    private fun fetchDiscoveredMovies() {
        launch {
            val result = retryIO(desc = "Discover Movies") { repo.discoverMoviesAsync(1).await() }
            if (result != null) {
                removeMoviesWithoutImages(result)
                discoverMovies.postValue(result.results.toImmutableList())
            }
        }
    }

    private fun removeMoviesWithoutImages(result: Tmdb.Discover) {
        val iterator = result.results.iterator()
        while (iterator.hasNext()) {
            val movie = iterator.next()
            if (movie.getImageUrl() === null) iterator.remove()
        }
    }

    fun onUserRefreshedMain() {
        fetchDiscoveredMovies()
    }

    fun onWindowFocusChanged(hasFocus: Boolean) {
        if (hasFocus && !isAppLaunchAnimated) {
            viewListener.postValue(EVENT_START_ANIMATION)
            isAppLaunchAnimated = true
        }
    }
}