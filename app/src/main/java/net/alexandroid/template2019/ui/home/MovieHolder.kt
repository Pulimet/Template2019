package net.alexandroid.template2019.ui.home

import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.android.synthetic.main.item_movie.view.*
import net.alexandroid.template2019.R
import net.alexandroid.template2019.loadImage
import net.alexandroid.template2019.model.Movie
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.ui.base.BaseHolder

class MovieHolder(v: View, private val homeViewModel: HomeViewModel) : BaseHolder(v) {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (adapterPosition != RecyclerView.NO_POSITION && v != null) {
            homeViewModel.isAnimated = true
            ViewCompat.setTransitionName(v.imgMovie, "imageViewAnim")
            val movie = v.tag as Movie
            movie.position = adapterPosition
            homeViewModel.onMovieSelected(movie)
        }
    }

    fun onBindViewHolder(data: Movie) {
        itemView.tag = data
        imgMovie.loadImage(data.getImageUrl(), holder = R.drawable.ic_no_video)
    }


}