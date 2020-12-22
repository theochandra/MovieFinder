package com.android.moviefinder.databinding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.squareup.picasso.Picasso

@BindingConversion
fun setVisibility(state: Boolean): Int {
    return if (state) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    url?.let {
        val url = "https://image.tmdb.org/t/p/w500$it"
        Picasso.get().load(url).into(imageView)
    }
}

@BindingAdapter("popularity")
fun bindPopularity(textView: TextView, popularity: Double) {
    textView.text = popularity.toString()
}