package com.redrain.sup_base.utils

import android.content.Context
import android.content.res.Resources

object NavigationBarUtil {

    fun getNavigationBarHeight(context: Context?): Int {
        if (context == null) return 0
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }
}