package com.alwe.plugins.systeminfo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.core.os.ConfigurationCompat
import com.getcapacitor.JSArray
import com.getcapacitor.JSObject
import java.util.TimeZone


class SystemInfo(
    private val plugin: SystemInfoPlugin,
    private val context: Context
) {
    private val installedFeatures = context.packageManager.systemAvailableFeatures.map { it.name }

    fun getUpdatedInfos(): JSObject {
        val content = JSObject()

        val ram = SystemProperties.getMemoryInfo(context)
        content.put("usedRAM", ram.totalMem - ram.availMem)

        val hdd = SystemProperties.getStorageSpace(Environment.getDataDirectory())
        content.put("usedHDD", hdd.second - hdd.first)

        val sd = SystemProperties.getStorageSpace(Environment.getExternalStorageDirectory())
        content.put("usedSD", sd.second - sd.first)

        // val usedCPU = 0
        // content.put("usedCPU", usedCPU)

        return content
    }

    @SuppressLint("HardwareIds")
    fun getInfos(): JSObject {
        val content = JSObject()
        val features = JSArray()

        // Software Information
        content.put("osName", "Android")
        content.put("osVersion", Build.VERSION.RELEASE)
        content.put("sdkVersion", Build.VERSION.SDK_INT)
        content.put("sdkName", getCodeName(Build.VERSION.SDK_INT))
        content.put("securityPatch", Build.VERSION.SECURITY_PATCH)
        content.put("uiVersion", Build.VERSION.INCREMENTAL)
        content.put("deviceID", Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID))
        content.put("locales", JSArray(ConfigurationCompat.getLocales(plugin.activity.resources.configuration).toLanguageTags().split(", ")))
        content.put("timezone", TimeZone.getDefault().id)
        content.put("brandName", Build.BRAND.toCapitalized())
        content.put("boardName", Build.BOARD.toCapitalized())
        content.put("bootloaderVersion", Build.BOOTLOADER.toCapitalized())
        content.put("supportedABIs", JSArray(Build.SUPPORTED_ABIS))

        val cpuCores = JSObject()
        cpuCores.put("maxFreq", JSArray(SystemProperties.getCPUInfo(InfoCPU.MAX_FREQUENCY).map {
            JSObject().put("freq", it.key).put("cores", it.value)
        }))
        cpuCores.put("minFreq", JSArray(SystemProperties.getCPUInfo(InfoCPU.MIN_FREQUENCY).map {
            JSObject().put("freq", it.key).put("cores", it.value)
        }))

        // Hardware Information
        content.put("manufacturer", Build.MANUFACTURER)
        content.put("modelID", Build.MODEL)
        content.put("modelCodeName", Build.DEVICE.toCapitalized())
        content.put("cpuModel", SystemProperties.getCPUModel())
        content.put("cpuCores", cpuCores)
        content.put("totalCores", Runtime.getRuntime().availableProcessors())
        content.put("totalRAM", SystemProperties.getMemoryInfo(context).totalMem)
        content.put("totalHDD", SystemProperties.getStorageSpace(Environment.getDataDirectory()).second)
        content.put("totalSD", SystemProperties.getStorageSpace(Environment.getExternalStorageDirectory()).second)

        installedFeatures.forEach { i -> featuresMap[i]?.let { features.put(it) } }
        content.put("features", features)

        return content
    }
}