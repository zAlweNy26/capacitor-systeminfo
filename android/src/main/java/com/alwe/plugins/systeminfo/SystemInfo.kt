package com.alwe.plugins.systeminfo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.util.Log
import com.getcapacitor.JSArray
import com.getcapacitor.JSObject
import java.util.Timer
import java.util.TimerTask


class SystemInfo(
    private val notify: (eventName: String, data: JSObject, retainUntilConsumed: Boolean) -> Unit,
    private val context: Context
) {
    private val timer = Timer("CheckUsage")
    private val installedFeatures = context.packageManager.systemAvailableFeatures.map { it.name }

    fun start() {
        Log.d("Start", "Alwe's SystemInfo Plugin")
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                getUpdatedInfos()
            }
        }, 1, 1000)
    }

    fun stop() {
        Log.d("Stop", "Alwe's SystemInfo Plugin")
        timer.cancel()
        timer.purge()
    }

    fun getUpdatedInfos() {
        val content = JSObject()

        val ram = SystemProperties.getMemoryInfo(context)
        val usedRAM = (ram.totalMem - ram.availMem).toGiga(true)
        content.put("usedRAM", usedRAM)

        val hdd = SystemProperties.getStorageSpace(Environment.getDataDirectory())
        val usedHDD = (hdd.second - hdd.first).toGiga(true)
        content.put("usedHDD", usedHDD)

        val sd = SystemProperties.getStorageSpace(Environment.getExternalStorageDirectory())
        val usedSD = (sd.second - sd.first).toGiga(true)
        content.put("usedSD", usedSD)

        //val usedCPU = 0
        //content.put("usedCPU", usedCPU)

        this.notify("runtimeChange", content, false)
    }

    @SuppressLint("HardwareIds")
    fun getInfos(): JSObject {
        val content = JSObject()
        val features = JSArray()

        val securityPatch = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            Build.VERSION.SECURITY_PATCH else SystemProperties.read("ro.build.version.security_patch")

        // Software Informations
        content.put("osName", "Android")
        content.put("osVersion", Build.VERSION.RELEASE)
        content.put("sdkVersion", Build.VERSION.SDK_INT)
        content.put("sdkName", getCodeName(Build.VERSION.SDK_INT))
        content.put("securityPatch", securityPatch)
        content.put("uiVersion", Build.VERSION.INCREMENTAL)
        content.put("deviceID", Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID))
        content.put("brandName", Build.BRAND.toCapitalized())
        content.put("boardName", Build.BOARD.toCapitalized())
        content.put("bootloaderVersion", Build.BOOTLOADER.toCapitalized())
        content.put("supportedABIs", JSArray(Build.SUPPORTED_ABIS))

        // Hardware Informations
        content.put("manufacturer", Build.MANUFACTURER)
        content.put("modelID", Build.MODEL)
        content.put("modelCodeName", Build.DEVICE.toCapitalized())
        content.put("cpuModel", SystemProperties.getCPUModel())
        content.put("cpuCores", JSArray(SystemProperties.getCPUInfo(InfoCPU.MAX_FREQUENCY).map { JSArray(it.toList()) }))
        content.put("totalCores", SystemProperties.getNumberOfCores())
        content.put("totalRAM", (SystemProperties.getMemoryInfo(context).totalMem).toGiga(true))
        content.put("totalHDD", SystemProperties.getStorageSpace(Environment.getDataDirectory()).second)
        content.put("totalSD", SystemProperties.getStorageSpace(Environment.getExternalStorageDirectory()).second)

        installedFeatures.forEach { i -> featuresMap[i]?.let { features.put(it) } }
        content.put("features", features)

        return content
    }
}