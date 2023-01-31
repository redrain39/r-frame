package com.redrain.sup_base.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * 虚拟键盘控制工具
 *
 * 键盘模式：
 * SOFT_INPUT_ADJUST_UNSPECIFIED 不指定调整方式，系统自行决定使用哪种调整方式
 * SOFT_INPUT_ADJUST_RESIZE      调整方式为布局需要重新计算大小适配当前可见区域
 * SOFT_INPUT_ADJUST_PAN         调整方式为布局需要整体移动
 * SOFT_INPUT_ADJUST_NOTHING     不做任何操作
 */
object SoftInputUtil {

    private var softInputHeight = 0
    private var softInputHeightChanged = false
    private var isSoftInputShowing = false

    interface ISoftInputChanged {
        fun onChanged(isSoftInputShow: Boolean, softInputHeight: Int, viewOffset: Int)
    }

    /**
     * 监听键盘的变化
     */
    fun attachSoftInput(anyView: View, listener: ISoftInputChanged) {
        // 根View
        val rootView = anyView.rootView
        // 虚拟导航栏的高度
        val navigationHeight = NavigationBarUtil.getNavigationBarHeight(anyView.context)
        // 虚拟导航栏是否显示
        var isNavigationBarShow = false

        rootView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            // 对于Activity来说，该高度即为屏幕高度
            val rootHeight = rootView.height
            val rect = Rect()
            // 获取当前可见部分，默认可见部分是除了状态栏和导航栏剩下的部分
            rootView.getWindowVisibleDisplayFrame(rect)
            if (rootHeight - rect.bottom == navigationHeight) {
                // 如果可见部分底部与屏幕底部刚好相差导航栏的高度，则认为有导航栏
                isNavigationBarShow = true
            } else if (rootHeight - rect.bottom == 0) {
                // 如果可见部分底部与屏幕底部平齐，说明没有导航栏
                isNavigationBarShow = false
            }

            var isSoftInputShow = false
            var softInputHeight = 0
            // 如果有导航栏，则要去除导航栏的高度
            val mutableHeight = if (isNavigationBarShow) navigationHeight else 0
            if (rootHeight - mutableHeight > rect.bottom) {
                // 除去导航栏高度后，可见区域仍然小于屏幕高度，则说明键盘弹起了
                isSoftInputShow = true
                // 键盘高度
                softInputHeight = rootHeight - mutableHeight - rect.bottom
                if (SoftInputUtil.softInputHeight != softInputHeight) {
                    softInputHeightChanged = true
                    SoftInputUtil.softInputHeight = softInputHeight
                } else {
                    softInputHeightChanged = false
                }
            }

            // 获取目标View的位置坐标
            val location = IntArray(2)
            anyView.getLocationOnScreen(location)

            // 条件1 减少不必要的回调，只关心前后发生变化的
            // 条件2 针对软键盘切换手写、拼音键等键盘高度发生变化
            if (isSoftInputShowing != isSoftInputShow || isSoftInputShow && softInputHeightChanged) {
                listener.onChanged(isSoftInputShow, softInputHeight,
                        location[1] + anyView.height - rect.bottom)
                isSoftInputShowing = isSoftInputShow
            }
        }
    }

    /**
     * 显示软键盘
     */
    fun showSoftInput(view: View) {
        val inputMethodManager = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, 0)
    }

    /**
     * 隐藏软键盘
     */
    fun hideSoftInput(view: View) {
        val inputMethodManager = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}