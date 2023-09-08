package com.alwe.plugins.systeminfo

import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin

@CapacitorPlugin(name = "SystemInfo")
class SystemInfoPlugin : Plugin() {
    private var implementation: SystemInfo? = null

    override fun load() {
        super.load()
        implementation = SystemInfo(::notifyListeners, context)
    }

    @PluginMethod
    fun start(call: PluginCall) {
        val res = implementation?.start()
        call.resolve(res)
    }

    @PluginMethod
    fun stop(call: PluginCall) {
        implementation?.stop()
    }
}