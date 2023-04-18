package jlmd.dev.android.catsapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadFromUrl(url: String){
    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .centerCrop()
        .into(this)
}