package com.flappy.wanandroid.ext

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.flappy.wanandroid.NavMainDirections

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:39 2022/10/18
 */
fun Fragment.goArticleDetail(title: String, url: String) {
    findNavController().navigate(
        NavMainDirections.actionHomeFragmentToWebFragment(title, url)
    )
}