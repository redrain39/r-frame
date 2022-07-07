package com.redrain.sup_base.module.base.impls

import android.graphics.Color
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
import androidx.fragment.app.FragmentManager
import com.redrain.sup_base.R
import com.redrain.sup_base.module.base.interfaces.IBaseDialogFragment

abstract class BaseDialogFragment<DB: ViewDataBinding>(
    @LayoutRes val contentLayoutId: Int
): DialogFragment(contentLayoutId), IBaseDialogFragment {

    lateinit var dataBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 获取传递参数
        arguments?.let {
            getIntentParam(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)

        // 设置dataBinding
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), contentLayoutId, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // 初始化视图
        initUI()
        // 初始化数据
        initObserver()
    }

    open fun getIntentParam(bundle: Bundle) {

    }
}