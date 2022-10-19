package com.flappy.wanandroid.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseActivity
import com.flappy.wanandroid.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationBarView.LabelVisibility

/**
 * @Author: luweiming
 * @Description: 首页
 * @Date: Created in 15:11 2022/8/25
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //构建闪屏页
        val splashScreen = installSplashScreen()
    }

    override fun observe() {
    }

    override fun initView() {
        val pagerAdapter = MainPagerAdapter(supportFragmentManager, lifecycle)
        //关联viewpager和bottomNavigationView
        binding.vpMain.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.btmNav.menu.getItem(position).isChecked = true

                }
            })
        }
        binding.btmNav.apply {
            setOnItemSelectedListener(object :NavigationBarView.OnItemSelectedListener{
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    when(item.itemId){
                        R.id.menu_home -> binding.vpMain.currentItem = 0
                        R.id.menu_system -> binding.vpMain.currentItem = 1
                        R.id.menu_wx -> binding.vpMain.currentItem = 2
                        R.id.menu_mine -> binding.vpMain.currentItem = 3
                    }
                    return true
                }
            })
            labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
        }
    }

    override fun getLayoutId() = R.layout.activity_main
}