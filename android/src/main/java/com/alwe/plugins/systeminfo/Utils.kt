package com.alwe.plugins.systeminfo

import android.content.pm.PackageManager
import kotlin.math.pow
import kotlin.math.roundToInt

enum class InfoCPU(val info: String) {
    MIN_FREQUENCY("cpuinfo_min_freq"),
    MAX_FREQUENCY("cpuinfo_max_freq"),
    CUR_FREQUENCY("scaling_cur_freq")
}

val featuresMap = mapOf(
    PackageManager.FEATURE_BLUETOOTH to "bluetooth",
    PackageManager.FEATURE_BLUETOOTH_LE to "bluetoothLowEnergy",
    PackageManager.FEATURE_MICROPHONE to "microphone",
    PackageManager.FEATURE_AUDIO_OUTPUT to "speaker",
    PackageManager.FEATURE_NFC to "nfc",
    PackageManager.FEATURE_CAMERA_ANY to "camera",
    PackageManager.FEATURE_GAMEPAD to "gamepad",
    PackageManager.FEATURE_LOCATION_GPS to "gps",
    PackageManager.FEATURE_TOUCHSCREEN to "touchscreen",
    PackageManager.FEATURE_WIFI to "wifi",
    PackageManager.FEATURE_FINGERPRINT to "fingerprint",
    PackageManager.FEATURE_FACE to "face",
    PackageManager.FEATURE_ETHERNET to "ethernet"
)

fun toGiga(base: Number, isByte: Boolean = false): Double {
    val giga = (if (isByte) 1024.0 else 1000.0).pow(3.0)
    return ((base.toDouble() / giga) * 100.0).roundToInt() / 100.0
}