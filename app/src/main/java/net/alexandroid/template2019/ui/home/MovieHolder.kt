package net.alexandroid.template2019.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.*
import net.alexandroid.template2019.R
import net.alexandroid.template2019.loadImage
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.ui.base.BaseHolder

class MovieHolder(v: View, private val mainViewModel: HomeViewModel) : BaseHolder(v) {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (adapterPosition != RecyclerView.NO_POSITION && v != null) {
            mainViewModel.onMovieSelected(v.tag as Tmdb.Movie)
        }
    }

    fun onBindViewHolder(data: Tmdb.Movie) {
        itemView.tag = data
        imgMovie.loadImage(data.getImageUrl(), holder = R.drawable.ic_no_video)
    }


}