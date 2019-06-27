package net.alexandroid.template2019.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.RecyclerView
import net.alexandroid.template2019.R
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.ui.base.BaseHolder


class HomeAdapter(private val homeViewModel: HomeViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = listOf<Tmdb.Movie>()
    private var offset = 200L
    private var launchTime = 0L
    private val lastPosition = -1

    init {
        launchTime = System.currentTimeMillis()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(v, homeViewModel)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieHolder).onBindViewHolder(data[position])
        addScaleAnimation(holder, position)
    }

    private fun addScaleAnimation(holder: RecyclerView.ViewHolder, position: Int) {
        if (position < lastPosition) return
        val anim = ScaleAnimation(
            0.0f, 1.0f, 0.0f, 1.0f,
            RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f
        )
        anim.duration = 800L
        anim.startOffset = offset
        offset = if (launchTime + 1000 > System.currentTimeMillis()) offset + 100 else 0
        holder.itemView.startAnimation(anim)
    }

    override fun getItemCount() = data.size

    fun setItems(items: Tmdb.Discover) {
        data = items.results
        notifyDataSetChanged()
    }

    //Called when a view created by this adapter has been attached to a window.
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        (holder as BaseHolder).onViewAttachedToWindow()
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation() // Solve issues with fast scrolling
        (holder as BaseHolder).onViewDetachedFromWindow()
    }
}