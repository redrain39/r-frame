package com.jianghu.sup_base.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.redrain.sup_base.R

class ProgressView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    @DrawableRes
    var progressBackgroundDrawable = 0
    @DrawableRes
    var progressDrawable = 0

    var progress = 0.0
        set(value) {
            field = value
            invalidate()
        }

    private val progressBackgroundPaint = Paint()
    private val progressPaint = Paint()
    private val rect = Rect()
    private val rectF = RectF()

    init {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ProgressView,
            0, 0
        )

        try {
            progressBackgroundDrawable = a.getResourceId(R.styleable.ProgressView_progressBackgroundDrawable, 0)
            progressDrawable = a.getResourceId(R.styleable.ProgressView_progressDrawable, 0)
        } finally {
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        rect.set(0, 0, width, height)
        rectF.set(0f, 0f, width.toFloat(), height.toFloat())
        ResourcesCompat.getDrawable(resources, progressBackgroundDrawable, null)
            ?.toBitmap(width, height)?.let { bitmap ->
                canvas.drawBitmap(bitmap, rect, rectF, progressBackgroundPaint)
            }

        rect.set(0, 0, (width * progress).toInt(), height)
        rectF.set(0f, 0f, (width * progress).toFloat(), height.toFloat())
        ResourcesCompat.getDrawable(resources, progressDrawable, null)
            ?.toBitmap(width, height)?.let { bitmap ->
                canvas.drawBitmap(bitmap, rect, rectF, progressPaint)
            }
    }

}