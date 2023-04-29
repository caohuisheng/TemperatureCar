package com.example

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

open class StatusBar:AppCompatActivity() {
    public companion object fun cancelTitle(){
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        if (Build.VERSION.SDK_INT >= 21) {
            var decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
}