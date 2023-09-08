package com.alwe.plugins.systeminfo

import android.app.ActivityManager
import android.content.Context
import android.os.StatFs
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.Scanner

object SystemProperties {
    fun read(propName: String): String {
        var process: Process? = null
        var bufferedReader: BufferedReader? = null
        return try {
            process = ProcessBuilder().command("/system/bin/getprop", propName)
                .redirectErrorStream(true).start()
            bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
            val line = bufferedReader.readLine() ?: ""
            Log.i("SystemInfo Plugin", "Read System Property: $propName=$line")
            line
        } catch (e: Exception) {
            Log.e("SystemInfo Plugin", "Failed to read System Property $propName", e)
            ""
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close()
                } catch (_: IOException) {}
            }
            process?.destroy()
        }
    }

    fun getStorageSpace(dir: File): Pair<Double, Double> {
        val statsFs = StatFs(dir.absolutePath)
        val total = toGiga(statsFs.blockCountLong * statsFs.blockSizeLong)
        val free = toGiga(statsFs.availableBlocksLong * statsFs.blockSizeLong)
        return Pair(free, total)
    }

    fun getCPUModel(): String {
        return readSystemFile("/proc/cpuinfo")
    }

    fun getCPUInfo(info: InfoCPU): Array<Pair<Double, Int>> {
        val totCores = Runtime.getRuntime().availableProcessors()
        val freqCount = mutableMapOf<Double, Int>()
        for (i in 0 until totCores) {
            val core = try { readSystemFile("/sys/devices/system/cpu/cpu$i/cpufreq/$info").toDouble() }
                catch (_: Exception) { 0.0 }
            if (core != 0.0) {
                val freq = toGiga(core)
                if (freqCount[freq] != null) freqCount[freq] = freqCount[freq]!!.inc()
                else freqCount[freq] = 1
            }
        }
        return freqCount.toList().toTypedArray()
    }

    private fun readSystemFile(path: String): String {
        return try {
            val process = ProcessBuilder("/system/bin/cat", path).start()
            val sb = StringBuilder()
            val sc = Scanner(process.inputStream)
            while (sc.hasNextLine()) { sb.append(sc.nextLine()) }
            sb.toString()
        } catch (e: Exception) { "" }
    }

    fun getMemoryInfo(context: Context): ActivityManager.MemoryInfo {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return ActivityManager.MemoryInfo().also { it ->
            activityManager.getMemoryInfo(it)
        }
    }
}