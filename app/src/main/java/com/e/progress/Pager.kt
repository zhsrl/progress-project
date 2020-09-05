package com.e.progress

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class Pager: ViewPager {
    private var swipable = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        swipable = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (swipable) {
            super.onTouchEvent(event)
        } else false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (swipable) super.onInterceptTouchEvent(ev) else false
    }

    fun setSwipable(swipe: Boolean) {
        swipable = swipe
    }

}