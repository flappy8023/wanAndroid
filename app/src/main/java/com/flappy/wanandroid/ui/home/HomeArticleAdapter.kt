package com.flappy.wanandroid.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.flappy.wanandroid.R
import com.flappy.wanandroid.data.model.Article

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:48 2022/9/1
 */
class HomeArticleAdapter : PagingDataAdapter<Article, HomeArticleAdapter.ArticleHolder>(DIFF_CALLBACK) {
    var itemClick:(Int, Article) ->Unit = { _Int, _Article->}
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

    inner class ArticleHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(article: Article?) {
            val tvTitle: TextView = view.findViewById(R.id.tv_title)
            tvTitle.text = article?.title
            view.setOnClickListener { itemClick(bindingAdapterPosition,article!!) }
        }

    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        return ArticleHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_item_article, parent, false)
        )
    }
}