package com.flappy.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:55 2022/9/7
 */
class AppBarView : Toolbar {
    var firstIcon = -1
    var secondIcon = -1
    var navIcon = -1
    var titleSize = 18
    var titleColor = resources.getColor(R.color.title_text_color)

    private lateinit var tvTitle: TextView
    private lateinit var ivNav: ImageView
    private lateinit var ivIcon1: ImageView
    private lateinit var ivIcon2: ImageView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        obtainAttributes(attributeSet)
        initView()
    }

    private fun obtainAttributes(attributeSet: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.AppBarView)
        firstIcon = typedArray.getResourceId(R.styleable.AppBarView_firstIcon, -1)
        secondIcon = typedArray.getResourceId(R.styleable.AppBarView_secondIcon, -1)
        navIcon = typedArray.getResourceId(R.styleable.AppBarView_navIcon, -1)
        titleSize = typedArray.getInt(R.styleable.AppBarView_titleSizeDp, 18)
        titleColor = typedArray.getColor(
            R.styleable.AppBarView_titleColor,
            resources.getColor(R.color.title_text_color)
        )
        typedArray.recycle()

    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.toolbar_layout, this, true)
        tvTitle = findViewById(R.id.tv_title)
        ivIcon1 = findViewById(R.id.iv_right_1)
        ivIcon2 = findViewById(R.id.iv_right_2)
        ivNav = findViewById(R.id.iv_nav)
        if (-1 == navIcon) {
            ivNav.visibility = View.GONE
        } else {
            ivNav.visibility = View.VISIBLE
            ivNav.setImageResource(navIcon)
        }
        if (-1 == firstIcon && -1 == secondIcon) {
            ivIcon1.visibility = View.GONE
            ivIcon2.visibility = View.GONE
        } else if (-1 != firstIcon && -1 != secondIcon) {
            ivIcon1.visibility = View.VISIBLE
            ivIcon2.visibility = View.VISIBLE
            ivIcon1.setImageResource(firstIcon)
            ivIcon2.setImageResource(secondIcon)
        } else {
            //如果仅设置了一个图片，那么都用icon1展示
            ivIcon2.visibility = GONE
            ivIcon1.visibility = VISIBLE
            ivIcon1.setImageResource(firstIcon)
        }
        tvTitle.setTextColor(titleColor)
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, titleSize.toFloat())
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }
}