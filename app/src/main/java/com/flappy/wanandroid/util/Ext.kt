package com.flappy.wanandroid.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:19 2022/8/24
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "wanAndroid")