package com.flappy.wanandroid.util

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.flappy.wanandroid.ui.web.WebActivity
import com.flappy.wanandroid.vo.Article

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:39 2022/10/18
 */
fun Fragment.goArticleDetail(title: String, url: String) {
    this.startActivity(Intent(this.requireContext(), WebActivity::class.java).apply {
        putExtra("title", title)
        putExtra("url", url)
    })
}
fun Fragment.articleClick(p:Int,article: Article){
    goArticleDetail(article.title,article.link)
}