package com.flappy.wanandroid.ui.home

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.flappy.wanandroid.R
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.databinding.HomeItemArticleBinding

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:48 2022/9/1
 */
class HomeArticleAdapter :
    PagingDataAdapter<Article, HomeArticleAdapter.ArticleHolder>(DIFF_CALLBACK) {
    var itemClick: (Int, Article) -> Unit = { _, _ -> }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    inner class ArticleHolder(private val binding: HomeItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(article: Article?) {
            article?.let {
                binding.apply {
                    tvTitle.text = Html.fromHtml(it.title)
                    tvAuthor.text = it.shareUser
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
                    root.setOnClickListener {
                        itemClick(
                            bindingAdapterPosition,
                            getItem(bindingAdapterPosition)!!
                        )
                    }

                }
            }
        }

    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        return ArticleHolder(
            HomeItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}