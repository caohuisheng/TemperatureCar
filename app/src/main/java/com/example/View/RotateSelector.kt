package com.example.View

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.R


class RotateSelector: ConstraintLayout {
    lateinit var view1: ViewGroup
    lateinit var view2: ViewGroup

    private var onSurface = true //表示view1是否可见

    constructor(context: Context) : super(context) {
        initView(null)
    }

    constructor(context: Context, attributeSet: AttributeSet? = null) : super(
        context,
        attributeSet
    ) {
        initView(attributeSet)
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyle: Int = 0
    ) : super(context, attributeSet, defStyle) {
        initView(attributeSet)
    }

    private fun initView(attributeSet: AttributeSet?) {
        val v = View.inflate(context, R.layout.rotate_selector,this);
        view1 = v.findViewById(R.id.selector_item1)
        view2 = v.findViewById(R.id.selector_item2)
        view2.visibility = GONE
        val a = context.obtainStyledAttributes(attributeSet,R.styleable.RotateSelector)
        val bg1 = a.getDrawable(R.styleable.RotateSelector_view1BackGroundColor)
        val bg2 = a.getDrawable(R.styleable.RotateSelector_view2BackGroundColor)
        view1.background = bg1
        view2.background = bg2
    }

    fun rotateView() {
        var view1Action =  ObjectAnimator()
        var view2Action = ObjectAnimator()
        var view3Action = ObjectAnimator()
        var view4Action = ObjectAnimator()
        if (onSurface) {

            view1Action = ObjectAnimator.ofFloat(view1,"rotationY",0f,90f,180f)
            view2Action = ObjectAnimator.ofFloat(view2,"rotationY",0f)
            view3Action = ObjectAnimator.ofFloat(view1,"Alpha",1f,0.5f,0f)
            view4Action = ObjectAnimator.ofFloat(view2,"Alpha",0f,0.5f,1f)
            view3Action.interpolator = AccelerateInterpolator()
            view1Action.interpolator = AccelerateInterpolator()
            view2Action.interpolator = DecelerateInterpolator()
            view4Action.interpolator = DecelerateInterpolator()
        } else {
            view2Action = ObjectAnimator.ofFloat(view2,"rotationY",0f,90f,180f)
            view1Action = ObjectAnimator.ofFloat(view1,"rotationY",-180f,-90f,0f)
            view2Action.interpolator = AccelerateInterpolator()
            view1Action.interpolator = DecelerateInterpolator()

            view4Action = ObjectAnimator.ofFloat(view2,"Alpha",1f,0.5f,0f)
            view3Action = ObjectAnimator.ofFloat(view1,"Alpha",0f,0.5f,1f)
            view4Action.interpolator = AccelerateInterpolator()
            view3Action.interpolator = DecelerateInterpolator()
        }

        view1Action.addListener(object:AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                if (onSurface) {
                    view1.visibility = GONE
                    view2.visibility = VISIBLE
                    onSurface = false
                } else {
                    onSurface = true
                    view2.visibility = GONE
                    view1.visibility = VISIBLE
                }
            }

            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                if (!onSurface) {

                } else {

                }
            }
        })

        val set = AnimatorSet()
        set.playTogether(view1Action,view2Action,view3Action,view4Action)

        set.duration = 500
        set.start()
    }

    fun getView1Data() {

    }

    fun getView2Data() {

    }


}