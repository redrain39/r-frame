package com.redrain.sup_base.widgets.dialogs

import com.redrain.sup_base.R
import com.redrain.sup_base.databinding.DialogSelectBinding
import com.redrain.sup_base.ui.module.impls.BaseDialogFragment

class SelectDialog: BaseDialogFragment<DialogSelectBinding>(R.layout.dialog_select) {

    var title = "提示"
    var content = ""
    var btnConfirmContent = "确定"
    var btnCancelContent = "取消"

    private var onConfirmClick: () -> Unit = {}
    fun setOnConfirmClick(onConfirmClick: () -> Unit) {
        this.onConfirmClick = onConfirmClick
    }

    private var onCancelClick: () -> Unit = {}
    fun setOnCancelClick(onCancelClick: () -> Unit) {
        this.onCancelClick = onCancelClick
    }

    override fun initUI() {
        dataBinding.tvTitle.text = title
        dataBinding.tvContent.text = content
        dataBinding.btnConfirm.text = btnConfirmContent
        dataBinding.btnConfirm.setOnClickListener {
            onConfirmClick()
            dismiss()
        }
        dataBinding.btnCancel.text = btnCancelContent
        dataBinding.btnCancel.setOnClickListener {
            onCancelClick()
            dismiss()
        }
    }

    override fun initObserver() {

    }
}