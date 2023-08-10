package com.flappy.wanandroid.ui.setting

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.flappy.wanandroid.R
import com.flappy.wanandroid.util.CacheManager
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:59 2022/12/5
 */
@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar?>(R.id.toolbar)
        val navController = findNavController()
        toolbar.setupWithNavController(navController)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root, rootKey)
        initPreference()
    }

    private fun initPreference() {
        val darkModePref = findPreference<ListPreference>("dark_mode")
        darkModePref?.setOnPreferenceChangeListener { preference, newValue ->
            kotlin.run {
                when (newValue) {
                    "auto" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    "night" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                true
            }
        }
        findPreference<Preference>("clear_cache")?.apply {
            summary = CacheManager.getTotalCacheSize(requireContext())
            setOnPreferenceClickListener {
                CacheManager.cleanApplicationCache(requireContext())
                summary = CacheManager.getTotalCacheSize(requireContext())
                true
            }
        }
        findPreference<Preference>("upgrade")?.apply {
            summary = com.flappy.util.AppUtil.getVersionName(requireContext())
        }
    }


}