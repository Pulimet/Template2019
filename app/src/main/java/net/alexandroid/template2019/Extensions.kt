package net.alexandroid.template2019

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

// Used to load an image
fun ImageView.loadImage(url: String?, @DrawableRes error: Int = 0, @DrawableRes holder: Int = 0) {
    val creator: RequestCreator = if (url.isNullOrEmpty()) {
        Picasso.get().load(holder)
    } else {
        Picasso.get().load(url)
    }
    if (error != 0) creator.error(error)
    if (holder != 0) creator.placeholder(holder)
    creator.into(this)
}