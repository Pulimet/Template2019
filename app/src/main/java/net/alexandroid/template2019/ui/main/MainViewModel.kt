package net.alexandroid.template2019.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.network.retryIO
import net.alexandroid.template2019.ui.base.BaseViewModel

const val EVENT_START_ANIMATION = 0

class MainViewModel(private val repo: MainRepository) : BaseViewModel() {

    private lateinit var viewListener: MutableLiveData<Int>
    private lateinit var discoverMovies: MutableLiveData<Tmdb.Discover>

    private var isAnimated = false

    fun getViewEvents(): LiveData<Int> {
        if (!::viewListener.isInitialized) {
            viewListener = MutableLiveData()
        }
        return viewListener
    }

    fun getDiscoveredMovies(): LiveData<Tmdb.Discover> {
        if (!::discoverMovies.isInitialized) {
            discoverMovies = MutableLiveData()
            fetDiscoveredMovies()
        }
        return discoverMovies
    }

    private fun fetDiscoveredMovies() {
        launch {
            val result = retryIO(desc = "Discover Movies") { repo.discoverMoviesAsync().await() }
            if (result != null) discoverMovies.postValue(result)
        }
    }

    fun onUserRefreshedMain() {
        fetDiscoveredMovies()
    }

    fun onWindowFocusChanged(hasFocus: Boolean) {
        if (hasFocus && !isAnimated) {
            viewListener.postValue(EVENT_START_ANIMATION)
            isAnimated = true
        }
    }
}