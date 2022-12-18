package com.flappy.wanandroid.ui.search

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.databinding.FragmentSearchBinding

class SearchFragment : BaseVMFragment<FragmentSearchBinding, SearchVM>() {

    /**
     * 搜索推荐+历史
     */
    private lateinit var recFragment: SearchRecFragment

    /**
     * 搜索结果
     */
    private lateinit var resultFragment: SearchResultFragment

    override fun initView() {
        val toolbar = (requireActivity() as AppCompatActivity).supportActionBar
        toolbar?.setDisplayShowTitleEnabled(false)
        toolbar?.setDisplayHomeAsUpEnabled(true)
        initFragment()
        binding.tvSearch.setOnClickListener { searchByKeyWords(binding.searchView.query.toString()) }
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByKeyWords(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    private fun searchByKeyWords(query: String?) {
        query?.let {
            if (childFragmentManager.backStackEntryCount == 0) {
                childFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .show(resultFragment).hide(recFragment).commitAllowingStateLoss()
            }
            resultFragment.doSearch(it)
        }
    }

    private fun initFragment() {
        recFragment = SearchRecFragment()
        resultFragment = SearchResultFragment()
        childFragmentManager.beginTransaction()
            .add(binding.container.id, recFragment, "rec")
            .add(binding.container.id, resultFragment, "result")
            .hide(resultFragment)
            .commitAllowingStateLoss()
    }

    override fun getLayoutId(): Int = R.layout.fragment_search
    override fun bindViewModel() {
    }
}