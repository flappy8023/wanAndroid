package com.flappy.wanandroid.ui.home

import android.text.Html
import android.view.View
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseRecyclerViewAdapter
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.databinding.HomeItemArticleBinding

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 21:11 2022/9/6
 */
class HomeTopAdapter : BaseRecyclerViewAdapter<Article, HomeItemArticleBinding>() {
    var itemClick: ((Int, Article) -> Unit)? = null

    override fun getLayoutId(): Int {
        return R.layout.home_item_article
    }

    override fun bindView(binding: HomeItemArticleBinding, data: Article, holder: Holder) {
        data.let {
            binding.apply {
                tvTitle.text = Html.fromHtml(it.title)
                tvAuthor.text = it.author
                tvDate.text = it.niceDate
                tvCategory.text = buildString {
                    append(it.superChapterName)
                    append("/")
                    append(it.chapterName)
                }
                //置顶文章
                if (it.type == 1) {
                    tvLabel.visibility = View.VISIBLE
                    tvLabel.text = tvLabel.context.getText(R.string.top)
                } else {
                    tvLabel.visibility = View.GONE
                }
            }
        }

        binding.root.setOnClickListener {
            itemClick?.invoke(holder.bindingAdapterPosition, data)
        }
    }
}