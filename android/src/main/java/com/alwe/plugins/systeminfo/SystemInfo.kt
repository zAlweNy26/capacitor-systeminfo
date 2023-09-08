package com.alwe.plugins.systeminfo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.util.Log
import com.getcapacitor.JSObject

class SystemInfo(
    private val notify: (eventName: String, data: JSObject, retainUntilConsumed: Boolean) -> Unit,
    private val context: Context
) {

    init {

    }

    fun start(): JSObject {
        val content = JSObject()
        Log.d("Start", "Alwe's SystemInfo Plugin")
        return content
    }

    fun stop() {
        Log.d("Start", "Alwe's SystemInfo Plugin")
    }
}