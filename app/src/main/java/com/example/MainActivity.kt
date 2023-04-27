package com.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.View.RotateSelector
import java.nio.channels.Selector

class MainActivity : AppCompatActivity() {

    private lateinit var selector: RotateSelector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selector = findViewById(R.id.rotate_selector)
        selector.setOnClickListener {view ->
            run {
                selector.rotateView()
            }
        }
    }
}