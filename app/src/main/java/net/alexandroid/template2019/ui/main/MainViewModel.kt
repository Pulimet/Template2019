package net.alexandroid.template2019.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.network.retryIO
import net.alexandroid.template2019.ui.base.BaseViewModel

class MainViewModel(private val repo: MainRepository) : BaseViewModel() {

    private lateinit var discoverMovies: MutableLiveData<Tmdb.Discover>

    fun getDiscoveredMovies(): LiveData<Tmdb.Discover> {
        if (!::discoverMovies.isInitialized) {
            discoverMovies = MutableLiveData()
            launch {
                val result = retryIO(desc = "Discover Movies") { repo.discoverMoviesAsync().await() }
                if (result != null) discoverMovies.postValue(result)
            }
        }
        return discoverMovies
    }
}