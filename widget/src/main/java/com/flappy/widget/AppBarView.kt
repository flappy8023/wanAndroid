package com.flappy.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:55 2022/9/7
 */
class AppBarView : Toolbar {
    var rightIcon = -1
    var navIcon = -1
    var titleSize = 18
    var titleColor = ActivityCompat.getColor(context, R.color.title_text_color)

    private lateinit var tvTitle: TextView
    private lateinit var ivNav: ImageView
    private lateinit var ivIcon: CircleImageView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        obtainAttributes(attributeSet)
        initView()
    }

    private fun obtainAttributes(attributeSet: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.AppBarView)
        rightIcon = typedArray.getResourceId(R.styleable.AppBarView_rightIcon, -1)
        navIcon = typedArray.getResourceId(R.styleable.AppBarView_navIcon, -1)
        titleSize = typedArray.getInt(R.styleable.AppBarView_titleSize, 18)
        titleColor = typedArray.getColor(
            R.styleable.AppBarView_titleColor,
            ActivityCompat.getColor(context, R.color.title_text_color)
        )
        typedArray.recycle()

    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.toolbar_layout, this, true)
        setContentInsetsAbsolute(0, 0)
        tvTitle = findViewById(R.id.tv_title)
        ivIcon = findViewById(R.id.iv_right)
        ivNav = findViewById(R.id.iv_nav)
        if (-1 == navIcon) {
            ivNav.visibility = View.GONE
        } else {
            ivNav.visibility = View.VISIBLE
            ivNav.setImageResource(navIcon)
        }
        if (-1 == rightIcon) {
            ivIcon.visibility = View.GONE
        } else {
            ivIcon.visibility = View.VISIBLE
            ivIcon.setImageResource(rightIcon)
        }
        tvTitle.setTextColor(titleColor)
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, titleSize.toFloat())
        ivIcon.setOnClickListener {

        }
    }

    fun setRightClickListener(listener: (View) -> Unit) {
        ivIcon.setOnClickListener(listener)
    }

    fun setLeftClickListener(listener: (View) -> Unit){
        ivNav.setOnClickListener(listener)
    }

    fun setLeftIcon(drawable: Drawable) {
        ivNav.setImageDrawable(drawable)
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }

    fun setRightIconLetter(drawable: Drawable, letter: String? = null) {
        ivIcon.setImageDrawable(drawable)
        ivIcon.setLetter(letter)
    }
}