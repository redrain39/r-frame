package com.jianghu.sup_base.func.screen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.greenrobot.eventbus.EventBus

class ScreenBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            Intent.ACTION_SCREEN_ON -> { // 屏幕亮
                EventBus.getDefault().post(ScreenStatusEvent(ScreenStatus.SCREEN_ON))
            }
            Intent.ACTION_SCREEN_OFF -> { // 屏幕暗
                EventBus.getDefault().post(ScreenStatusEvent(ScreenStatus.SCREEN_OFF))
            }
            Intent.ACTION_USER_PRESENT -> { // 屏幕解锁
                EventBus.getDefault().post(ScreenStatusEvent(ScreenStatus.SCREEN_PRESENT))
            }
        }
    }
}