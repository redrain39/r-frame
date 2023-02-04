package com.redrain.sup_base.widgets.settingbar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.databinding.DataBindingUtil
import com.redrain.sup_base.R
import com.redrain.sup_base.databinding.WidgetSettingSwitchBarBinding
import com.suke.widget.SwitchButton

class SettingSwitchBar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private val dataBinding: WidgetSettingSwitchBarBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.widget_setting_switch_bar,
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

    val btnSwitch: SwitchButton
        get() {
            return dataBinding.btnSwitch
        }

    init {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SettingSwitchBar,
            0, 0
        )

        try {
            leftTextColor = a.getColor(R.styleable.SettingSwitchBar_leftTextColor, Color.BLACK)
            leftText = a.getString(R.styleable.SettingSwitchBar_leftText)
        } finally {
            a.recycle()
        }

        dataBinding.tvText.setTextColor(leftTextColor)
        dataBinding.tvText.text = leftText
    }
}