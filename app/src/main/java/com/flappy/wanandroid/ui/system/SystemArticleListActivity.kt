package com.flappy.wanandroid.ui.system

import android.content.Context
import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseActivity
import com.flappy.wanandroid.databinding.SystemArticleListBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import kotlinx.coroutines.flow.collectLatest

/**
 * @Author: luweiming
 * @Description:体系二级目录下文章列表
 * @Date: Created in 22:02 2022/10/17
 */
class SystemArticleListActivity : BaseActivity<SystemArticleListBinding, SystemVM>() {
    private val adapter = HomeArticleAdapter()

    companion object {
        fun start(context: Context, cid: Long) =
            context.startActivity(Intent(context, SystemArticleListActivity::class.java).apply {
                putExtra("cid", cid)
            })
    }

    override fun handleArguments() {
        intent?.let {
            viewModel.cid = it.getLongExtra("cid", -1)
        }
    }

    override fun initView() {
        binding.rvArticles.adapter = adapter
    }

    override fun getLayoutId(): Int = R.layout.system_article_list

    override fun observe() {
        lifecycleScope.launchWhenCreated {
            viewModel.treeArticles.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}