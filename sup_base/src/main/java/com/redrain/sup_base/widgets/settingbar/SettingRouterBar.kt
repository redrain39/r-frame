package com.redrain.sup_base.widgets.settingbar

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.databinding.DataBindingUtil
import com.redrain.sup_base.R
import com.redrain.sup_base.databinding.WidgetSettingRouterBarBinding

class SettingRouterBar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private val dataBinding: WidgetSettingRouterBarBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.widget_setting_router_bar,
        this, true
    )

    @ColorInt
    var leftTextColor: Int = Color.BLACK
        set(value) {
            field = value
            dataBinding.tvText.setTextColor(value)
        }

    var leftText: String? = ""
        set(value) {
            field = value
            dataBinding.tvText.text = value
        }

    var rightTextColor: Int = Color.BLACK
        set(value) {
            field = value
            dataBinding.tvHint.setTextColor(value)
        }

    var rightText: String? = ""
        set(value) {
            field = value
            dataBinding.tvHint.text = value
        }

    var rightImage: Drawable? = null
        set(value) {
            field = value
            dataBinding.ivImage.setImageDrawable(value)
        }

    init {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SettingRouterBar,
            0, 0
        )

        try {
            rightImage = a.getDrawable(R.styleable.SettingRouterBar_rightIcon)
            leftTextColor = a.getColor(R.styleable.SettingRouterBar_leftTextColor, Color.BLACK)
            leftText = a.getString(R.styleable.SettingRouterBar_leftText)
            rightTextColor = a.getColor(R.styleable.SettingRouterBar_rightTextColor, Color.BLACK)
            rightText = a.getString(R.styleable.SettingRouterBar_rightText)
        } finally {
            a.recycle()
        }

        if (rightImage == null) {
            dataBinding.ivImage.visibility = View.GONE
        } else {
            dataBinding.ivImage.visibility = View.VISIBLE
            dataBinding.ivImage.setImageDrawable(rightImage)
        }
        dataBinding.tvText.setTextColor(leftTextColor)
        dataBinding.tvText.text = leftText
        dataBinding.tvHint.setTextColor(rightTextColor)
        dataBinding.tvHint.text = rightText
    }
}