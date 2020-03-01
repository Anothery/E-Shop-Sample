package com.example.eshopsample.utils

import android.view.View

fun View.fadeIn() {
    this.visibility = View.VISIBLE
    this.animate()
        .setDuration(250)
        .alpha(1.0f)


}

fun View.fadeOut() {
    this.animate()
        .setDuration(250)
        .alpha(0.0f)
        .withEndAction {
            this.visibility = View.GONE
        }

}