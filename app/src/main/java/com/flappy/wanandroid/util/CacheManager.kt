package com.flappy.wanandroid.util

import android.content.Context
import android.os.Environment
import java.io.File

/**
 * @Author: luweiming
 * @Description:缓存管理类
 * @Date: Created in 16:23 2022/12/16
 */
object CacheManager {

    /**
     * 清理内置控件缓存,data/data/package/cache
     *
     * @param context
     */
    fun cleanInternalCache(context: Context) {
        deleteFilesByDirectory(context.cacheDir)
    }

    /**
     * 清除数据库
     *
     * @param context
     */
    fun cleanDatabase(context: Context) {
        deleteFilesByDirectory(File("data/data/${context.packageName}/databases"))
    }

    fun cleanSharedPreferences(context: Context) {
        deleteFilesByDirectory(File("data/data/${context.packageName}/shared_prefs"))
    }

    /**
     * 清空指定数据库
     *
     * @param context
     * @param name
     */
    fun cleanDatabaseByName(context: Context, name: String) {
        context.deleteDatabase(name)
    }

    fun cleanExternalCache(context: Context) {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            deleteFilesByDirectory(context.externalCacheDir)
        }
    }

    /**
     * 清理整个应用的缓存
     *
     * @param context
     */
    fun cleanApplicationCache(context: Context) {
        cleanExternalCache(context)
        cleanInternalCache(context)
    }

    private fun deleteFilesByDirectory(directory: File?) {
        if (null == directory) return
        if (directory.exists() && directory.isDirectory) {
            directory.listFiles()
                ?.forEach { if (it.isDirectory) deleteFilesByDirectory(it) else it.delete() }
        }
    }


    fun getTotalCacheSize(context: Context): String {
        val internalCache = getFolderSize(context.cacheDir)
        var externalCache: Long = 0
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            externalCache = getFolderSize(context.externalCacheDir)
        }
        return getFormatSize(internalCache + externalCache)
    }

    private fun getFolderSize(file: File?): Long {
        if (file == null) return 0
        var size = 0L
        val fileList = file.listFiles()
        fileList.forEach {
            if (it.isDirectory) {
                size += getFolderSize(it)
            } else {
                size += it.length()
            }
        }
        return size
    }

    private fun getFormatSize(size: Long): String {
        val kb: Long = size / 1024
        val m: Int = (kb / 1024).toInt()
        val kbs: Long = kb % 1024
        return "${m}.${kbs}MB"
    }


}