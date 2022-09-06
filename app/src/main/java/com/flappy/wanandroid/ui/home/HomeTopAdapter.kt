package com.flappy.wanandroid.ui.home

import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseRecyclerViewAdapter
import com.flappy.wanandroid.databinding.HomeItemArticleBinding
import com.flappy.wanandroid.vo.Article

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 21:11 2022/9/6
 */
class HomeTopAdapter:BaseRecyclerViewAdapter<Article,HomeItemArticleBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.home_item_article
    }

    override fun bindView(binding: HomeItemArticleBinding, data: Article, holder: Holder) {
        binding.tvTitle.text = data.title
    }
}