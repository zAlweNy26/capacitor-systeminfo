package com.alwe.plugins.systeminfo

import android.util.Log
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.JSObject
import com.getcapacitor.annotation.CapacitorPlugin
import java.util.Timer
import java.util.TimerTask

@CapacitorPlugin(name = "SystemInfo")
class SystemInfoPlugin : Plugin() {
    private var implementation: SystemInfo? = null
    private var timer: Timer? = null

    override fun load() {
        super.load()
        implementation = SystemInfo(this, context)
    }

    @PluginMethod
    fun getInfos(call: PluginCall) {
        call.resolve(implementation?.getInfos())
    }

    @PluginMethod
    fun start(call: PluginCall) {
        Log.d("SystemInfo Plugin", "Start listening")
        timer?.cancel()
        timer?.purge()
        timer = Timer("CheckUsage")
        timer?.schedule(object : TimerTask() {
            override fun run() {
                activity.runOnUiThread {
                    val content = implementation?.getUpdatedInfos()
                    if (content != null) notifyListeners("runtimeChange", content, true)
                }
            }
        }, 100, 1000) // Start after 100ms, repeat every second
    }

    @PluginMethod
    fun stop(call: PluginCall) {
        Log.d("SystemInfo Plugin", "Stop listening")
        timer?.cancel()
        timer?.purge()
    }
}