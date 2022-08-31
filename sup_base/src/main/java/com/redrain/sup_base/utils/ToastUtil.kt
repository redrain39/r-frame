package com.redrain.sup_base.utils

import android.content.Context
import android.widget.Toast

object ToastUtil {

    private var sContext: Context? = null

    fun init(context: Context) {
        sContext = context.applicationContext
    }

    fun showShortToast(content: String?) {
        Toast.makeText(sContext, content, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(content: String?) {
        Toast.makeText(sContext, content, Toast.LENGTH_LONG).show()
    }
}