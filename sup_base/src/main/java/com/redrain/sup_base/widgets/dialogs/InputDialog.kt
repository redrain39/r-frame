package com.redrain.sup_base.widgets.dialogs

import android.text.TextUtils
import com.redrain.sup_base.R
import com.redrain.sup_base.databinding.DialogInputBinding
import com.redrain.sup_base.ui.module.impls.BaseDialogFragment

class InputDialog: BaseDialogFragment<DialogInputBinding>(R.layout.dialog_input) {

    var title = "提示"
    var content = ""
    var hint = ""
    var btnConfirmContent = "确定"
    var btnCancelContent = "取消"

    private var onConfirmClick: (content: String) -> Unit = {}
    fun setOnConfirmClick(onConfirmClick: (content: String) -> Unit) {
        this.onConfirmClick = onConfirmClick
    }

    private var onCancelClick: () -> Unit = {}
    fun setOnCancelClick(onCancelClick: () -> Unit) {
        this.onCancelClick = onCancelClick
    }

    override fun initUI() {
        dataBinding.tvTitle.text = title
        dataBinding.etContent.hint = hint
        if (!TextUtils.isEmpty(content)) dataBinding.etContent.setText(content)
        dataBinding.btnConfirm.text = btnConfirmContent
        dataBinding.btnConfirm.setOnClickListener {
            onConfirmClick(dataBinding.etContent.text.toString())
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