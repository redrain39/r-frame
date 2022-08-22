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
import com.redrain.sup_base.widgets.list.adapter.interfaces.IBaseAdapter

abstract class BaseAdapter<DB : ViewDataBinding, D>(
    open val context: Context
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<DB>>(), IBaseAdapter<DB, D> {

    var dataSource = mutableListOf<D>()

    open fun setData(dataSource: MutableList<D>) {
        this.dataSource = dataSource
        notifyDataSetChanged()
    }

    open fun addData(dataSource: MutableList<D>) {
        this.dataSource.addAll(dataSource)
        notifyItemRangeInserted(this.dataSource.size - dataSource.size, dataSource.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DB> {
        val dataBinding: DB = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            getLayout(), parent, false
        )
        return BaseViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DB>, position: Int) {
        if (dataSource.size > 0) {
            onBind(holder, dataSource[position], position)
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    class BaseViewHolder<DB : ViewDataBinding>(val dataBinding: DB) :
        RecyclerView.ViewHolder(dataBinding.root) {
        var countDownTimer: CountDownTimer? = null
    }
}