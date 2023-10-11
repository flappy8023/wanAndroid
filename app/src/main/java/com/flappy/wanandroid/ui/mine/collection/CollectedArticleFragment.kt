package com.flappy.wanandroid.ui.mine.collection

import androidx.lifecycle.lifecycleScope
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.databinding.CommonListFragmentBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:28 2022/12/7
 */
@AndroidEntryPoint
class CollectedArticleFragment : BaseVMFragment<CommonListFragmentBinding, CollectionVM>() {
    private var adapter: HomeArticleAdapter? = null
    override fun observe() {
    }

    override fun initView() {
        adapter = HomeArticleAdapter()
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch {
            viewModel.getCollectedArticles().collectLatest {
                adapter?.submitData(it)
            }
        }

    }

    override fun getLayoutId(): Int = R.layout.common_list_fragment
}