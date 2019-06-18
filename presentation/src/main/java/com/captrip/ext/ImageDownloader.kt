package com.captrip.ext

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageDownloader {

    private val imageDownloader = Picasso.get()

    fun load(url: String, imageView: ImageView) {
        imageDownloader.load(url)
                .into(imageView)
    }
}