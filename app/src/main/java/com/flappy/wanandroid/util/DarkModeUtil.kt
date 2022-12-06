package com.flappy.wanandroid.util

import androidx.appcompat.app.AppCompatDelegate

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:26 2022/12/6
 */
object DarkModeUtil {
    fun initDarkMode(mode: String?) {
        setDarkMode(
            when (mode) {
                "light" -> AppCompatDelegate.MODE_NIGHT_NO
                "night" -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
    }

    private fun setDarkMode(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}