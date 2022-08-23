package com.redrain.sup_base.utils

import android.content.res.Resources
import android.util.DisplayMetrics

/**
 * 屏幕参数工具
 */
object ScreenUtil {

    private fun getDisplayMetrics(): DisplayMetrics {
        return Resources.getSystem().displayMetrics
    }

    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(): Int {
        return getDisplayMetrics().widthPixels
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenHeight(): Int {
        return getDisplayMetrics().heightPixels
    }
}