package com.flappy.wanandroid.util

import android.content.Intent
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.flappy.wanandroid.NavMainDirections
import com.flappy.wanandroid.R
import com.flappy.wanandroid.ui.web.WebFragment
import com.flappy.wanandroid.vo.Article

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