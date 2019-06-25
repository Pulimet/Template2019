package net.alexandroid.template2019.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer, View.OnClickListener {
    override fun onClick(v: View?) {}

    open fun onViewAttachedToWindow() {}

    open fun onViewDetachedFromWindow() {}
}
