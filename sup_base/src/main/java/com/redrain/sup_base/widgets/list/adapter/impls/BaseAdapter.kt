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

    private var dataSource = mutableListOf<D>()

    open fun setDataSource(dataSource: List<D>) {
        this.dataSource = dataSource.toMutableList()
        notifyDataSetChanged()
    }

    open fun addData(position: Int, data: D) {
        this.dataSource.add(position, data)
        notifyItemInserted(position)
    }

    open fun addDataSource(dataSource: List<D>) {
        this.dataSource.addAll(dataSource)
        notifyItemRangeInserted(this.dataSource.size - dataSource.size, dataSource.size)
    }

    open fun addDataSource(startPosition: Int, dataSource: List<D>) {
        this.dataSource.addAll(startPosition, dataSource)
        notifyItemRangeInserted(startPosition, dataSource.size)
    }

    open fun removeData(position: Int) {
        this.dataSource.removeAt(position)
        notifyItemRemoved(position)
    }

    open fun removeData(data: D) {
        val position = this.dataSource.indexOf(data)
        this.dataSource.remove(data)
        notifyItemRemoved(position)
    }

    open fun removeDataSource(startPosition: Int, dataSource: List<D>) {
        this.dataSource.removeAll(dataSource)
        notifyItemRangeRemoved(startPosition, dataSource.size)
    }

    open fun updateData(position:Int, data: D) {
        this.dataSource[position] = data
        notifyItemChanged(position)
    }

    open fun updateDataSource(startPosition: Int, dataSource: List<D>) {
        for ((index, data) in dataSource.withIndex()) {
            this.dataSource[index + startPosition] = data
        }
        notifyItemRangeChanged(startPosition, dataSource.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DB> {
        val dataBinding: DB = DataBindingUtil.inflate(
            LayoutInflater.from(context), getLayout(), parent, false
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