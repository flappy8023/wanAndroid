package com.flappy.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:16 2022/10/18
 */
class CircleImageView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    def: Int = 0
) :
    AppCompatImageView(context, attributeSet, def) {
    private var path: Path = Path()
    private var srcPath: Path = Path()
    private var _width = 0f
    private var _height = 0f
    private var paint: Paint = Paint()
    private lateinit var srcRectF: RectF
    private var xferMode: Xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        _width = w.toFloat()
        _height = h.toFloat()
        srcRectF = RectF(0f, 0f, _width, _height)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.saveLayer(srcRectF, null)
        super.onDraw(canvas)
        path.reset()
        srcPath.reset()
        path.addCircle(_width / 2, _height / 2, _width / 2, Path.Direction.CW)
        paint.isAntiAlias = true
        paint.xfermode = xferMode
        srcPath.addRect(srcRectF, Path.Direction.CW)
        srcPath.op(path, Path.Op.DIFFERENCE)
        canvas?.drawPath(srcPath, paint)
        //清楚xfermode
        paint.xfermode = null
        canvas?.restore()


    }


}