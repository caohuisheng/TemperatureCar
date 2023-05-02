package com.example.View

import android.view.MotionEvent
import android.view.View

class CustomTouchEvent : View.OnTouchListener {
    var pressAction: (() -> Unit)? = null
    var endAction: (() -> Unit)? = null
    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                pressAction?.let { it() }
            }
            MotionEvent.ACTION_UP ->
                endAction?.let { it() }

        }
        return true
    }

}