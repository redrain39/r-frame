package com.redrain.sup_base.module.base.impls

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.redrain.sup_base.R

abstract class BaseBottomDialogFragment<DB: ViewDataBinding>(
    @LayoutRes contentLayoutId: Int
): BaseDialogFragment<DB>(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置弹窗主题
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)
        // 启用弹窗取消
        isCancelable = true
        // 获取传递参数
        arguments?.let {
            getIntentParam(it)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.apply {
            setGravity(Gravity.BOTTOM)
            decorView.setPadding(0, 0, 0, 0)
            attributes = dialog.window?.attributes?.apply {
                width = WindowManager.LayoutParams.MATCH_PARENT
            }
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // 初始化视图
        initUI()
        // 初始化数据
        initObserver()
    }
}