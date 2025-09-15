package com.alwe.plugins.systeminfo

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.StatFs
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.Scanner

object SystemProperties {
    private fun readSystemFile(path: String): String {
        val sb = mutableListOf<String>()
        try {
            val process = ProcessBuilder("/system/bin/cat", path).start()
            val sc = Scanner(process.inputStream)
            while (sc.hasNextLine()) { sb.add(sc.nextLine()) }
        } catch (e: Exception) {
            Log.e("SystemInfo Plugin", "Failed to read System File $path")
        }
        return sb.joinToString("\n")
    }

    fun getProp(propName: String): String {
        var process: Process? = null
        var bufferedReader: BufferedReader? = null
        return try {
            process = ProcessBuilder().command("/system/bin/getprop", propName)
                .redirectErrorStream(true).start()
            bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
            val line = bufferedReader.readLine() ?: "Unknown"
            Log.i("SystemInfo Plugin", "Read System Property: $propName=$line")
            line
        } catch (e: Exception) {
            Log.e("SystemInfo Plugin", "Failed to read System Property $propName", e)
            "Unknown"
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close()
                } catch (_: IOException) {}
            }
            process?.destroy()
        }
    }

    fun getStorageSpace(dir: File): Pair<Long, Long> {
        val statsFs = StatFs(dir.absolutePath)
        val total = statsFs.blockCountLong * statsFs.blockSizeLong
        val free = statsFs.availableBlocksLong * statsFs.blockSizeLong
        return Pair(free, total)
    }

    fun getCPUModel(): String {
        val cpuInfo = readSystemFile("/proc/cpuinfo")
        val keywords = listOf("Model Name", "Hardware", "processor", "chip revision")

        for (keyword in keywords) {
            for (line in cpuInfo.lines()) {
                if (line.contains(keyword, true)) {
                    val value = line.substringAfter(':', "").trim()
                    if (value.isNotEmpty()) return value
                }
            }
        }

        return Build.HARDWARE ?: "Unknown"
    }

    fun getCPUInfo(type: InfoCPU): MutableMap<Double, Int> {
        val freqCount = mutableMapOf<Double, Int>()
        for (i in 0 until Runtime.getRuntime().availableProcessors()) {
            val core = readSystemFile("/sys/devices/system/cpu/cpu$i/cpufreq/${type.info}").toSafeDouble()
            if (core != 0.0) {
                if (freqCount[core] != null) freqCount[core] = freqCount[core]!!.inc()
                else freqCount[core] = 1
            }
        }
        return freqCount
    }

    fun getMemoryInfo(context: Context): ActivityManager.MemoryInfo {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return ActivityManager.MemoryInfo().also { it ->
            activityManager.getMemoryInfo(it)
        }
    }
}