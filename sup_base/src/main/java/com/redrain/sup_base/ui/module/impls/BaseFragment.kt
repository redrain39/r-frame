package com.redrain.sup_base.ui.module.impls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.redrain.sup_base.ui.module.interfaces.IBaseFragment
import com.redrain.sup_base.widgets.dialogs.LoadingDialog
import org.greenrobot.eventbus.EventBus

abstract class BaseFragment<DB: ViewDataBinding>(
    @LayoutRes val contentLayoutId: Int
): Fragment(), IBaseFragment {

    lateinit var dataBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 注册EventBus
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        // 初始化 ViewModel （部分场景使用，一般通过 by viewmodels 来实现）
        initViewModel()
        // 初始化状态栏
        initStatusBar()
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
        // 设置dataBinding
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), contentLayoutId, container, false)
        // 初始化视图
        initUI()
        // 初始化数据
        initObserver()
        return dataBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        // 注销EventBus
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }

    open fun initViewModel() {

    }

    open fun initStatusBar() {

    }

    open fun getIntentParam(bundle: Bundle) {

    }

    open fun useEventBus(): Boolean {
        return false
    }

    private val loadingDialog by lazy { LoadingDialog() }

    fun showLoadingDialog() {
        loadingDialog.show(childFragmentManager, "LoadingDialog")
    }

    fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }
}