package com.redrain.sup_base.utils

import android.util.Log
import com.redrain.sup_base.BuildConfig
import java.lang.Exception

/**
 * 日志工具
 */
object LogUtil {

    fun d(tag: String, content: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, content)
        }
    }

    fun d(tag: String, content: String, exception: Exception) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, content, exception)
        }
    }

    fun e(tag: String, content: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, content)
        }
    }

    fun e(tag: String, content: String, exception: Exception) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, content, exception)
        }
    }

    fun i(tag: String, content: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, content)
        }
    }

    fun i(tag: String, content: String, exception: Exception) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, content, exception)
        }
    }

    fun v(tag: String, content: String) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, content)
        }
    }

    fun v(tag: String, content: String, exception: Exception) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, content, exception)
        }
    }

    fun w(tag: String, content: String) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, content)
        }
    }

    fun w(tag: String, content: String, exception: Exception) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, content, exception)
        }
    }

    fun w(tag: String, exception: Exception) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, exception)
        }
    }

}