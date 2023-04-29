package com.example.View

import android.view.View

import androidx.viewpager2.widget.ViewPager2

class CustomPageTransformer: ViewPager2.PageTransformer {
    private val maxScale = 1.0f
    private val minScale = 0.85f

    override fun transformPage(page: View, position: Float) {
        if(position <= 1) {
            val scale = minScale + (maxScale - minScale) * (1 - Math.abs(position))
            page.scaleX = scale

            if (position > 0) {
                page.translationX = -2 * scale
            } else if(position < 0) {
                page.translationX = 2 * scale
            }
            page.scaleY = scale
        } else {
            page.scaleX = minScale
            page.scaleY = minScale
        }
    }
}