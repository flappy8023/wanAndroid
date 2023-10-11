package com.flappy.wanandroid.ui.home

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseRecyclerViewAdapter
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.data.model.BannerItem
import com.flappy.wanandroid.databinding.HomeItemArticleBinding
import com.flappy.wanandroid.databinding.HomeItemBannerBinding
import com.youth.banner.indicator.CircleIndicator

/**
 * @Author flappy8023
 * @Description //TODO
 * @Date 2023年08月11日 10:27
 **/
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

class HomeBannerAdapter() : BaseRecyclerViewAdapter<BannerItem, HomeItemBannerBinding>() {

    override fun getItemCount(): Int {
        //作为首页的一个item展示
        return if (getDataList().isEmpty()) 0 else 1
    }

    override fun getLayoutId(): Int {
        return R.layout.home_item_banner
    }

    override fun bindView(
        binding: HomeItemBannerBinding,
        data: BannerItem,
        holder: BaseRecyclerViewAdapter<BannerItem, HomeItemBannerBinding>.Holder
    ) {
        binding.banner.apply {
            setAdapter(ImageBannerAdapter(getDataList()))
            indicator = CircleIndicator(holder.itemView.context)
            setOnBannerListener { data, position ->
                itemClick?.invoke(
                    position,
                    data as BannerItem
                )
            }
        }
    }
}


class HomePageAdapter(val fragments: List<Fragment>, fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }


}

class HomeTopAdapter : BaseRecyclerViewAdapter<Article, HomeItemArticleBinding>() {

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