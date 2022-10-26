package com.flappy.wanandroid.ui.main

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.flappy.wanandroid.R
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @Author: luweiming
 * @Description: activity容器
 * @Date: Created in 15:11 2022/8/25
 */
class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    /**
     * 底部导航栏进入动画
     */
    private val btmEnterAnim by lazy {
        ObjectAnimator.ofFloat(
            btmNavigationView,
            "translationY",
            btmNavigationView.height.toFloat(),
            0f
        )
    }

    /**
     * 底部导航栏退出动画
     */
    private val btmExitAnim by lazy {
        ObjectAnimator.ofFloat(
            btmNavigationView,
            "translationY",
            0f,
            btmNavigationView.height.toFloat()
        ).apply {
            addListener(onEnd = {
                //推出后隐藏控件
                btmNavigationView.visibility = View.GONE
            })
        }
    }
    private lateinit var btmNavigationView: BottomNavigationView

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
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
            if (destination.id == R.id.homeFragment || destination.id == R.id.systemFragment || destination.id == R.id.wxFragment || destination.id == R.id.mineFragment) {
                if (btmNavigationView.visibility == View.GONE) {
                    btmNavigationView.visibility = View.VISIBLE
                    btmEnterAnim.start()
                }
            } else {
                btmExitAnim.start()
            }
        }
    }
}