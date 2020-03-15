package net.alexandroid.template2019.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.RecyclerView
import net.alexandroid.template2019.R
import net.alexandroid.template2019.model.Movie
import net.alexandroid.template2019.ui.base.BaseHolder


class HomeAdapter(private val homeViewModel: HomeViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = listOf<Movie>()
    private var offset = 0L

    private var launchTime = 0L

    init {
        launchTime = System.currentTimeMillis()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(v, homeViewModel)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieHolder).onBindViewHolder(data[position])
        addScaleAnimation(holder)
    }

    private fun addScaleAnimation(holder: RecyclerView.ViewHolder) {
        if (homeViewModel.isAnimated || System.currentTimeMillis() > launchTime + 1500) return

        val anim = ScaleAnimation(
            0.0f, 1.0f, 0.0f, 1.0f,
            RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f
        )
        anim.duration = 700L
        anim.startOffset = offset
        offset += 150
        holder.itemView.startAnimation(anim)
    }

    override fun getItemCount() = data.size

    fun setItems(items: List<Movie>) {
        data = items
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