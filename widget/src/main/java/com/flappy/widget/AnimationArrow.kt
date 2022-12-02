package com.flappy.widget

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * @Author: luweiming
 * @Description: 带有旋转动画的箭头
 * @Date: Created in 11:05 2022/11/28
 */
class AnimationArrow @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attributeSet, defStyleAttr) {
    private var stateListener: ((Boolean) -> Unit)? = null
    private var isExpand = false

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.AnimationArrow)
        isExpand = typedArray.getBoolean(R.styleable.AnimationArrow_isExpand, false)
        typedArray.recycle()
        setOnClickListener {
            toggle()
            stateListener?.invoke(isExpand)
        }

    }

    fun setOnStateChangeListener(listener: (Boolean) -> Unit) {
        stateListener = listener
    }

    /**
     * 展开动画
     */
    private var expandAnimator: Animator? = null

    /**
     * 折叠动画
     */
    private var collapseAnimator: Animator? = null

    fun toggle() {
        if (isExpand) {
            collapse()
        } else {
            expand()
        }
    }

    fun expand() {
        isExpand = true
        expandAnimator?.cancel()
        collapseAnimator?.cancel()
        expandAnimator?.start()
    }

    fun collapse() {
        isExpand = false
        expandAnimator?.cancel()
        collapseAnimator?.cancel()
        collapseAnimator?.start()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        expandAnimator = ObjectAnimator.ofFloat(this, "rotation", 0f, 180f).apply { duration = 500 }
        collapseAnimator =
            ObjectAnimator.ofFloat(this, "rotation", 180f, 0f).apply { duration = 500 }
        if (isExpand) expand() else collapse()

    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        expandAnimator?.cancel()
        collapseAnimator?.cancel()
        expandAnimator = null
        collapseAnimator = null
    }


}