package com.redrain.sup_base.widgets

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.redrain.sup_base.R
import com.redrain.sup_base.databinding.WidgetTitleBarBinding

class TitleBar(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private val dataBinding: WidgetTitleBarBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.widget_title_bar, this, true)

    private var onLeftClick: () -> Unit = {}
    fun setOnLiftClick(onLeftClick: () -> Unit) {
        this.onLeftClick = onLeftClick
    }
    private var onRightClick: () -> Unit = {}
    fun setOnRightClick(onRightClick: () -> Unit) {
        this.onRightClick = onRightClick
    }

    @ColorInt
    var bgColor: Int = Color.WHITE
        set(value) {
            field = value
            dataBinding.container.setBackgroundColor(value)
        }

    var leftIcon: Drawable? = null
        set(value) {
            field = value
            dataBinding.ivLeft.setImageDrawable(value)
        }

    var centerText: String? = null
        set(value) {
            field = value
            dataBinding.tvTitle.text = value
        }

    @ColorInt
    var centerTextColor: Int = Color.BLACK
        set(value) {
            field = value
            dataBinding.tvTitle.setTextColor(value)
        }

    var rightIcon: Drawable? = null
        set(value) {
            field = value
            dataBinding.ivRight.setImageDrawable(value)
        }

    var isRightIconShow: Boolean = true
        set(value) {
            field = value
            dataBinding.ivRight.visibility = if (value) VISIBLE else GONE
        }

    var rightText: String? = null
        set(value) {
            field = value
            dataBinding.tvRight.text = value
        }

    @ColorInt
    var rightTextColor: Int = Color.BLACK
        set(value) {
            field = value
            dataBinding.tvRight.setTextColor(value)
        }

    var isLineShow: Boolean = true
        set(value) {
            field = value
            dataBinding.divideLine.visibility = if (value) VISIBLE else GONE
        }

    init {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TitleBar,
            0, 0
        )

        try {
            bgColor = a.getColor(R.styleable.TitleBar_backgroundColor, Color.WHITE)
            leftIcon = a.getDrawable(R.styleable.TitleBar_leftIcon)
            centerText = a.getString(R.styleable.TitleBar_centerText)
            centerTextColor = a.getColor(R.styleable.TitleBar_centerTextColor, Color.parseColor("#353B55"))
            rightIcon = a.getDrawable(R.styleable.TitleBar_rightIcon)
            rightText = a.getString(R.styleable.TitleBar_rightText)
            rightTextColor = a.getColor(R.styleable.TitleBar_centerTextColor, Color.parseColor("#353B55"))
            isLineShow = a.getBoolean(R.styleable.TitleBar_isLineShow, true)
        } finally {
            a.recycle()
        }

        dataBinding.container.setBackgroundColor(bgColor)
        dataBinding.ivLeft.apply {
            setImageDrawable(if (leftIcon == null) ResourcesCompat.getDrawable(resources,
                    R.drawable.ic_arrow_black_left, null) else leftIcon)
            setOnClickListener {
                onLeftClick()
            }
        }
        dataBinding.tvTitle.apply {
            text = centerText
            setTextColor(centerTextColor)
        }
        if (rightText == null && rightIcon == null) {
            dataBinding.ivRight.visibility = INVISIBLE
            dataBinding.tvRight.visibility = INVISIBLE
        } else {
            if (rightText != null) {
                dataBinding.tvRight.apply {
                    text = rightText
                    setTextColor(rightTextColor)
                }
                dataBinding.ivRight.visibility = INVISIBLE
            }
            if (rightIcon != null) {
                dataBinding.ivRight.apply {
                    setImageDrawable(rightIcon)
                }
                dataBinding.tvRight.visibility = INVISIBLE
            }
        }
        dataBinding.tvRight.setOnClickListener {
            onRightClick()
        }
        dataBinding.ivRight.setOnClickListener {
            onRightClick()
        }
        dataBinding.divideLine.visibility = if (isLineShow) VISIBLE else GONE
    }

    fun bindActivity(activity: Activity) {
        onLeftClick = {
            activity.finish()
        }
    }
}