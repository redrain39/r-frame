package com.redrain.sup_base.ui.module.impls

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.redrain.sup_base.ui.module.interfaces.IBaseActivity
import com.redrain.sup_base.utils.DisplayUtil
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity<DB : ViewDataBinding>(
    @LayoutRes val contentLayoutId: Int
) : AppCompatActivity(), IBaseActivity {

    lateinit var dataBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 注册EventBus
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        // 初始化状态栏
        initStatusBar()
        // 获取传递参数
        getIntentParam()
        // 设置dataBinding
        dataBinding = DataBindingUtil.setContentView(this, contentLayoutId)
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

    private var fontScale = 1f

    override fun getResources(): Resources {
        val resources = super.getResources()
        return DisplayUtil.getResources(this, resources, fontScale)
    }

    override fun attachBaseContext(newBase: Context?) {
        if (newBase == null) super.attachBaseContext(newBase)
        else super.attachBaseContext(DisplayUtil.attachBaseContext(newBase, fontScale))
    }

    /**
     * 动态设置应用内文字大小
     */
    fun setFontScale(fontScale: Float) {
        this.fontScale = fontScale
        DisplayUtil.recreate(this)
    }

    open fun initStatusBar() {

    }

    open fun getIntentParam() {

    }

    open fun useEventBus(): Boolean {
        return false
    }
}