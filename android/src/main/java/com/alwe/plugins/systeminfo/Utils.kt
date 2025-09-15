package com.alwe.plugins.systeminfo

import android.content.pm.PackageManager

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

fun getCodeName(sdk: Int): String {
    return when {
        sdk >= 36 -> "Baklava"
        sdk >= 35 -> "Vanilla Ice Cream"
        sdk >= 34 -> "Upside Down Cake"
        sdk >= 33 -> "Tiramisu"
        sdk >= 31 -> "Snow Cone"
        sdk >= 30 -> "Red Velvet Cake"
        sdk >= 29 -> "Quince Tart"
        sdk >= 28 -> "Pie"
        sdk >= 26 -> "Oreo"
        sdk >= 24 -> "Nougat"
        sdk >= 23 -> "Marshmallow"
        else -> "Unknown"
    }
}

fun String.toSafeDouble() = try { this.toDouble() } catch (_: Exception) { 0.0 }

fun String.toCapitalized() = this.lowercase().replaceFirstChar { it.titlecase() }