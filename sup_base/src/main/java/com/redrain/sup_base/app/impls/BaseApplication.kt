package com.redrain.sup_base.app.impls

import android.app.Application
import com.redrain.sup_base.app.interfaces.IBaseApplication
import com.redrain.sup_base.utils.ToastUtil

abstract class BaseApplication: Application(), IBaseApplication {

    override fun onCreate() {
        super.onCreate()
        ToastUtil.init(this)
    }

}