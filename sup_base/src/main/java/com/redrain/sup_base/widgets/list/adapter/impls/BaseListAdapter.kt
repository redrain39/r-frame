package com.redrain.sup_base.widgets.list.adapter.impls

import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.redrain.sup_base.widgets.list.adapter.interfaces.IBaseListAdapter

abstract class BaseListAdapter<DB : ViewDataBinding, D>(
    open val context: Context,
    diffCallback: DiffUtil.ItemCallback<D>
) : ListAdapter<D, BaseListAdapter.BaseViewHolder<DB>>(diffCallback), IBaseListAdapter<DB, D> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DB> {
        val dataBinding: DB = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            getLayout(), parent, false
        )
        return BaseViewHolder(dataBinding)
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder<DB>, position: Int) {
        onBind(viewHolder, getItem(position), position)
    }

    class BaseViewHolder<DB : ViewDataBinding>(val dataBinding: DB) :
        RecyclerView.ViewHolder(dataBinding.root) {
        var countDownTimer: CountDownTimer? = null
    }
}