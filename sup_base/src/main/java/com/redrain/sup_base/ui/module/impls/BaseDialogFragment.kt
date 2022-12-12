package com.redrain.sup_base.ui.module.impls

import android.app.Dialog
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.redrain.sup_base.manager.AppManager
import com.redrain.sup_base.ui.module.interfaces.IBaseDialogFragment
import org.greenrobot.eventbus.EventBus

abstract class BaseDialogFragment<DB: ViewDataBinding>(
    @LayoutRes val contentLayoutId: Int
): DialogFragment(contentLayoutId), IBaseDialogFragment {

    lateinit var dataBinding: DB

    var isShowShade = false // 是否显示阴影遮罩

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 注册EventBus
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        // 获取传递参数
        arguments?.let {
            getIntentParam(it)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 设置dataBinding
        dataBinding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // App是否变灰
        if (AppManager.isAppBeGray) {
            val paint = Paint()
            val cm = ColorMatrix()
            cm.setSaturation(0f)
            paint.colorFilter = ColorMatrixColorFilter(cm)
            dialog?.window?.decorView?.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
        }
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (isShowShade) dialog?.window?.setDimAmount(0f)
        // 初始化视图
        initUI()
        // 初始化数据
        initObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        // 注销EventBus
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }

    open fun getIntentParam(bundle: Bundle) {

    }

    open fun useEventBus(): Boolean {
        return false
    }
}