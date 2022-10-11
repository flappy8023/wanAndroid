package xyz.flappy.statusmanager

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:13 2022/9/22
 */
class StatusLayout : ConstraintLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
}