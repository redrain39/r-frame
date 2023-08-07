package com.redrain.sup_base.widgets.list.adapter.interfaces

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.redrain.sup_base.widgets.list.adapter.impls.BaseListAdapter

interface IBaseListAdapter<DB: ViewDataBinding, D> {

    @LayoutRes
    fun getLayout(): Int

    fun onBind(viewHolder: BaseListAdapter.BaseViewHolder<DB>, item: D, position: Int)
}