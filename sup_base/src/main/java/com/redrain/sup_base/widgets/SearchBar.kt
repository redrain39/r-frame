package com.redrain.sup_base.widgets

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.redrain.sup_base.R
import com.redrain.sup_base.databinding.WidgetSearchBarBinding
import com.redrain.sup_base.utils.SoftInputUtil

class SearchBar(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val dataBinding: WidgetSearchBarBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.widget_search_bar,
        this, true
    )

    private var onSearchClick: (content: String) -> Unit = {}
    fun setOnSearchClick(onSearchClick: (content: String) -> Unit) {
        this.onSearchClick = onSearchClick
    }

    var hint = ""
        set(value) {
            field = value
            dataBinding.etSearch.hint = value
        }
    var content = ""
        set(value) {
            field = value
            dataBinding.etSearch.setText(value)
        }

    init {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SearchBar,
            0, 0
        )

        try {
            hint = a.getString(R.styleable.SearchBar_searchBarHint) ?: ""
        } finally {
            a.recycle()
        }

        dataBinding.etSearch.hint = hint
        dataBinding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearchClick(dataBinding.etSearch.text.toString())

                dataBinding.etSearch.clearFocus()
                SoftInputUtil.hideSoftInput(dataBinding.etSearch)
                true
            }
            false
        }
        dataBinding.tvSearch.setOnClickListener {
            onSearchClick(dataBinding.etSearch.text.toString())

            dataBinding.etSearch.clearFocus()
            SoftInputUtil.hideSoftInput(dataBinding.etSearch)
        }

        dataBinding.etSearch.requestFocus()
    }

    fun bindActivity(activity: Activity) {
        dataBinding.ivBack.setOnClickListener {
            activity.finish()
        }
    }
}