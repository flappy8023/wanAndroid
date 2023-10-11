package com.flappy.wanandroid.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.flappy.util.switchStatusBarLightMode
import com.flappy.wanandroid.R
import com.flappy.webview.WebViewPool
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: luweiming
 * @Description: activity容器
 * @Date: Created in 15:11 2022/8/25
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var btmNavigationView: BottomNavigationView

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        switchStatusBarLightMode(true)
        val navFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navFragment.navController
        btmNavigationView = findViewById<BottomNavigationView>(R.id.btm_nav)
        btmNavigationView.apply {
            setupWithNavController(navController)
            labelVisibilityMode = BottomNavigationView.LABEL_VISIBILITY_LABELED
        }
        initBtmNavBarBehavior()
    }

    private fun initBtmNavBarBehavior() {
        //监听页面跳转，离开首页几个页签后隐藏下方导航栏
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment || destination.id == R.id.systemFragment || destination.id == R.id.wechatFragment || destination.id == R.id.todoFragment) {
                btmNavigationView.visibility = View.VISIBLE
            } else {
                btmNavigationView.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        WebViewPool.get().destroy()
    }
}