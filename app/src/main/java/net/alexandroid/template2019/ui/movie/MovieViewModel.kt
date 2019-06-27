package net.alexandroid.template2019.ui.movie

import kotlinx.coroutines.launch
import net.alexandroid.template2019.model.Movie
import net.alexandroid.template2019.repos.MovieRepository
import net.alexandroid.template2019.ui.base.BaseViewModel

class MovieViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {

    fun onStarClick(movie: Movie) {
        launch { movieRepository.insert(movie) }
    }

}