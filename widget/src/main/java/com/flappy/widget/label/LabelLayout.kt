package com.flappy.widget.label

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.TextView
import com.flappy.widget.R

/**
 * @Author: luweiming
 * @Description:标签流式布局
 * @Date: Created in 23:08 2022/9/19
 */
class LabelLayout : ViewGroup {
    private var labelBgResource: Int = R.drawable.default_label_bg
    private var labelTextSize: Float = 18f
    private var labelTextColor: Int = context.resources.getColor(R.color.title_text_color)
    private var labelPaddingH: Int = 20
    private var labelPaddingV = 8
    private var labelMargin = 20

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initView()
    }

    private fun initView() {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var height = 0
        val maxWidth = MeasureSpec.getSize(widthMeasureSpec)
        var lineOffset = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)

            //一行放不下，需要从下一行重新计算，重置下一行的偏移量，增加高度
            if (lineOffset + child.measuredWidth > maxWidth || i == 0) {
                lineOffset = 0
                height += child.measuredHeight + labelMargin
            }
            lineOffset += child.measuredWidth + labelMargin

        }
        setMeasuredDimension(maxWidth, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var lineOffset = 0
        var line = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (lineOffset + child.measuredWidth > measuredWidth) {

                line++
                lineOffset = 0
            }
            child.layout(
                lineOffset,
                line * (child.measuredHeight + labelMargin),
                lineOffset + child.measuredWidth,
                (line + 1) * child.measuredHeight + line * labelMargin
            )
            lineOffset += child.measuredWidth + labelMargin
        }
    }


    fun setLabels(labels: List<String>) {
        for (label in labels) {
            val textView = TextView(context)
            val param = MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            param.marginStart = labelMargin
            param.topMargin = labelMargin
            textView.setPadding(labelPaddingH, labelPaddingV, labelPaddingH, labelPaddingV)
            textView.apply {
                text = label
                setTextColor(labelTextColor)
                textSize = labelTextSize
                background = context.getDrawable(labelBgResource)
            }
            addView(textView, param)
        }
    }
}