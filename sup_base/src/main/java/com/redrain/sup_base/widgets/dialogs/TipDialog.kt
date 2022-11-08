package com.redrain.sup_base.widgets.dialogs

import com.redrain.sup_base.R
import com.redrain.sup_base.databinding.DialogTipBinding
import com.redrain.sup_base.ui.module.impls.BaseDialogFragment

class TipDialog: BaseDialogFragment<DialogTipBinding>(R.layout.dialog_tip) {

    var title = "提示"
    var content = ""
    var btnConfirmContent = "确定"

    private var onConfirmClick: () -> Unit = {}
    fun setOnConfirmClick(onConfirmClick: () -> Unit) {
        this.onConfirmClick = onConfirmClick
    }

    override fun initUI() {
        dataBinding.tvTitle.text = title
        dataBinding.tvContent.text = content
        dataBinding.btnConfirm.text = btnConfirmContent
        dataBinding.btnConfirm.setOnClickListener {
            onConfirmClick()
        }
    }

    override fun initObserver() {

    }
}