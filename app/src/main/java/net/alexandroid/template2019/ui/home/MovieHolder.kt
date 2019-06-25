package net.alexandroid.template2019.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.*
import net.alexandroid.template2019.R
import net.alexandroid.template2019.TMDB_IMG_URL
import net.alexandroid.template2019.loadImage
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.ui.base.BaseHolder
import net.alexandroid.template2019.ui.main.MainViewModel

class MovieHolder(v: View, mainViewModel: MainViewModel) : BaseHolder(v) {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            // TODO viewModel.onDemoClick()
        }
    }

    fun onBindViewHolder(data: Tmdb.Movie) {
        imgMovie.loadImage(getImageUrl(data), holder = R.drawable.ic_no_video)
    }

    private fun getImageUrl(data: Tmdb.Movie) =
        when {
            data.posterImg != null -> TMDB_IMG_URL + data.posterImg
            data.backImg != null -> TMDB_IMG_URL + data.backImg
            else -> null
        }

}