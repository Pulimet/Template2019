package net.alexandroid.template2019.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.fragment_movie.*
import net.alexandroid.template2019.R
import net.alexandroid.template2019.loadImage
import net.alexandroid.template2019.model.Movie
import net.alexandroid.template2019.ui.base.BaseFragment
import net.alexandroid.utils.mylogkt.logD

import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment() {
    private lateinit var movie: Movie
    private var isGoBackCalled = false
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { movie = MovieFragmentArgs.fromBundle(it).movie }
        logD("Movie title is: ${movie.title}")

        imgMoviePoster.loadImage(movie.getImageUrl(), holder = R.drawable.ic_no_video)
        tvTitle.text = movie.getTitleWithYear()
        tvDescription.text = movie.overview
        tvRating.text = String.format("Rating: %s", movie.vote.toString())

        ivStar.setOnClickListener {
            movieViewModel.onStarClick(movie)
            Toast.makeText(context, "Movie added to favorites", Toast.LENGTH_SHORT).show()
        }

    }

    private fun goBack() {
        if (!isGoBackCalled) {
            isGoBackCalled = true
            findNavController().popBackStack()
        }
    }
}