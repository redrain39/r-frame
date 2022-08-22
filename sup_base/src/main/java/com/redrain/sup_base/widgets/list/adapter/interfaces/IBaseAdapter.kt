package com.redrain.sup_base.widgets.list.adapter.interfaces

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.redrain.sup_base.widgets.list.adapter.impls.BaseAdapter

interface IBaseAdapter<DB: ViewDataBinding, D> {

    @LayoutRes
    fun getLayout(): Int

    fun onBind(viewHolder: BaseAdapter.BaseViewHolder<DB>, item: D, position: Int)
}
