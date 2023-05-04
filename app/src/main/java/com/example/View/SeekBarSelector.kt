package com.example.View

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.TextView
import com.example.R

class SeekBarSelector:FrameLayout {

    private lateinit var name: TextView
    private lateinit var seekBar:SeekBar
    private lateinit var value: TextView

    constructor(context: Context):super(context) {
        initView(null)
    }

    constructor(context: Context, attributeSet: AttributeSet):super(context,attributeSet) {
        initView(attributeSet)
    }

    constructor(context: Context,attributeSet: AttributeSet,defStyleAttr: Int):super(context,attributeSet,defStyleAttr) {
        initView(attributeSet)
    }


    fun initView(attributeSet: AttributeSet?) {
        val view: View? = LayoutInflater.from(context).inflate(R.layout.selector_layout,this@SeekBarSelector,true)
        name = view?.findViewById(R.id.seekBar_text) ?: TextView(context)
        seekBar = view?.findViewById(R.id.seekBar_scroll) ?: SeekBar(context)
        value = view?.findViewById(R.id.seekBar_value) ?: TextView(context)
        val a = context.obtainStyledAttributes(attributeSet,R.styleable.SeekBarSelector)
        val name = a.getString(R.styleable.SeekBarSelector_name)
        this.name.text = name
        val color = a.getColor(R.styleable.SeekBarSelector_valueColor,Color.BLACK)
        this.value.setTextColor(color)
        val tc = a.getColor(R.styleable.SeekBarSelector_nameColor,Color.WHITE)
        this.name.setTextColor(tc)
        val maxValue = a.getInt(R.styleable.SeekBarSelector_maxValue,100)
        val minValue = a.getInt(R.styleable.SeekBarSelector_minVlaue,0)
        value.text = ((maxValue + minValue) / 2 ).toString()
        val type = a.getString(R.styleable.SeekBarSelector_type)
        var flag = false
        if (type == "Int") flag = true
        seekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (seekBar != null) {
                    if (flag) {
                        value.text = String.format("%d",
                            (minValue + progress * (maxValue - minValue) / 100))
                    } else {
                        value.text = String.format("%.2f",
                            (minValue + progress * (maxValue - minValue) * 0.01f))
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        a.recycle()
    }
}