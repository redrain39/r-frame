package com.redrain.sup_base.widgets.dialogs

import android.os.Bundle
import com.redrain.sup_base.R
import com.redrain.sup_base.databinding.DialogLoadingBinding
import com.redrain.sup_base.ui.module.impls.BaseDialogFragment

class LoadingDialog: BaseDialogFragment<DialogLoadingBinding>(R.layout.dialog_loading) {

    companion object {
        private const val CONTENT = "content"

        fun newInstance(content: String) = LoadingDialog().apply {
            arguments = Bundle().apply {
                putString(CONTENT, content)
            }
        }
    }

    var content = "加载中"

    override fun getIntentParam(bundle: Bundle) {
        content = bundle.getString(CONTENT, "")
    }

    override fun initUI() {
        dataBinding.tvContent.text = content
    }

    override fun initObserver() {

    }
}