package com.flappy.wanandroid.ui.mine.collection

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.CommonListFragmentBinding
import com.flappy.wanandroid.ui.home.HomeArticleAdapter
import kotlinx.coroutines.flow.collectLatest

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:28 2022/12/7
 */
class CollectedArticleFragment : BaseFragment<CommonListFragmentBinding>() {
    private val viewModel: CollectionVM by viewModels(ownerProducer = { requireParentFragment() })
    private var adapter: HomeArticleAdapter? = null
    override fun initView() {
        adapter = HomeArticleAdapter()
        binding.recyclerView.adapter = adapter
        lifecycleScope.launchWhenCreated {
            viewModel.getCollectedArticles().collectLatest {
                adapter?.submitData(it)
            }
        }

    }

    override fun getLayoutId(): Int = R.layout.common_list_fragment
}