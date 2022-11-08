package com.redrain.sup_base.utils

import android.util.Log
import com.redrain.sup_base.BuildConfig
import com.redrain.sup_base.manager.AppManager
import java.lang.Exception

/**
 * 日志工具
 */
object LogUtil {

    fun d(tag: String, content: String) {
        if (AppManager.isDebug) {
            Log.d(tag, content)
        }
    }

    fun d(tag: String, content: String, exception: Exception) {
        if (AppManager.isDebug) {
            Log.d(tag, content, exception)
        }
    }

    fun e(tag: String, content: String) {
        if (AppManager.isDebug) {
            Log.e(tag, content)
        }
    }

    fun e(tag: String, content: String, exception: Exception) {
        if (AppManager.isDebug) {
            Log.e(tag, content, exception)
        }
    }

    fun i(tag: String, content: String) {
        if (AppManager.isDebug) {
            Log.i(tag, content)
        }
    }

    fun i(tag: String, content: String, exception: Exception) {
        if (AppManager.isDebug) {
            Log.i(tag, content, exception)
        }
    }

    fun v(tag: String, content: String) {
        if (AppManager.isDebug) {
            Log.v(tag, content)
        }
    }

    fun v(tag: String, content: String, exception: Exception) {
        if (AppManager.isDebug) {
            Log.v(tag, content, exception)
        }
    }

    fun w(tag: String, content: String) {
        if (AppManager.isDebug) {
            Log.w(tag, content)
        }
    }

    fun w(tag: String, content: String, exception: Exception) {
        if (AppManager.isDebug) {
            Log.w(tag, content, exception)
        }
    }

    fun w(tag: String, exception: Exception) {
        if (AppManager.isDebug) {
            Log.w(tag, exception)
        }
    }

}