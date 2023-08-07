package com.redrain.sup_base.ui.viewmodel.impls

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.redrain.sup_base.manager.AppManager
import com.redrain.sup_base.manager.LogTagManager
import com.redrain.sup_base.ui.viewmodel.interfaces.IBaseViewModel
import com.redrain.sup_base.utils.LogUtil
import com.redrain.sup_base.utils.ToastUtil
import com.redrain.sup_base.widgets.list.RefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseAndroidViewModel(
    application: Application
): AndroidViewModel(application), IBaseViewModel {

    val loadingState by lazy { MutableLiveData<Boolean>() }
    val refreshState = MutableLiveData<RefreshState>()

    fun launch(
        onLaunch: suspend CoroutineScope.() -> Unit,
        onError: suspend CoroutineScope.(e: Exception) -> Unit = {},
        onComplete: suspend CoroutineScope.() -> Unit = {}
    ) {
        viewModelScope.launch {
            if (AppManager.isDebug) {
                try {
                    onLaunch()
                } finally {
                    onComplete()
                }
            } else {
                try {
                    onLaunch()
                } catch (e: Exception) {
                    onError(e)
                    ToastUtil.showShortToast("网络请求错误，请稍后再试")
                    LogUtil.e(LogTagManager.TAG_NETWORK, e.toString())
                } finally {
                    onComplete()
                }
            }
        }.invokeOnCompletion { t ->
            if (t != null) LogUtil.e(LogTagManager.TAG_NETWORK, t.toString())
        }
    }

}