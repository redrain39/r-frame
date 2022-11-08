package com.redrain.rframe.mvi.ui.view.activity

import com.redrain.rframe.R
import com.redrain.rframe.databinding.ActivityMainBinding
import com.redrain.sup_base.ui.module.impls.BaseActivity
import com.redrain.sup_base.widgets.dialogs.InputDialog

class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initUI() {
        dataBinding.btnTest.setOnClickListener {
            InputDialog().apply {
                title = ""
            }.show(supportFragmentManager, "")
        }
    }

    override fun initObserver() {

    }
}