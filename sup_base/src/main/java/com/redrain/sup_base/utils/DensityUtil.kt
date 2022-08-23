package com.redrain.sup_base.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * 像素密度工具
 *
 * 相关概念：
 * px: 像素
 * dp: 屏幕密度的抽象单位
 * sp: 根据用户的字体大小首选项进行缩放
 * pt: 磅
 * in: 英寸
 * mm: 毫米
 */
object DensityUtil {

    /**
     * dp to px
     * @param value     dp value
     * @return          px value
     */
    fun dp2px(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                Resources.getSystem().displayMetrics)
    }

    /**
     * sp to px
     * @param value     sp value
     * @return          px value
     */
    fun sp2px(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value,
                Resources.getSystem().displayMetrics)
    }

    /**
     * pt to px
     * @param value     pt value
     * @return          px value
     */
    fun pt2px(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, value,
                Resources.getSystem().displayMetrics)
    }

    /**
     * in to px
     * @param value     in value
     * @return          px value
     */
    fun in2px(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_IN, value,
                Resources.getSystem().displayMetrics)
    }

    /**
     * mm to px
     * @param value     mm value
     * @return          px value
     */
    fun mm2px(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, value,
                Resources.getSystem().displayMetrics)
    }
}