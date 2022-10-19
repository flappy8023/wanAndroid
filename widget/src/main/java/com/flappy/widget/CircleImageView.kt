package com.flappy.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:16 2022/10/18
 */
class CircleImageView(context: Context, attributeSet: AttributeSet) :
    AppCompatImageView(context, attributeSet) {
    private lateinit var path:Path
    init {
        path = Path()
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}