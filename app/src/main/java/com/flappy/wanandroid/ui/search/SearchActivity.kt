package com.flappy.wanandroid.ui.search

import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseActivity
import com.flappy.wanandroid.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchVM>() {

    /**
     * 搜索推荐+历史
     */
    private lateinit var recFragment: SearchRecFragment

    /**
     * 搜索结果
     */
    private lateinit var resultFragment: SearchResultFragment
    override fun observe() {

    }

    override fun initView() {
        setSupportActionBar(binding.toolbar)
        val toolbar = supportActionBar
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

    private fun searchByKeyWords(query:String?){
        query?.let {
            if (supportFragmentManager.backStackEntryCount == 0) {
                supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .show(resultFragment).hide(recFragment).commitAllowingStateLoss()
            }
            resultFragment.doSearch(it)
        }
    }

    private fun initFragment() {
        recFragment = SearchRecFragment()
        resultFragment = SearchResultFragment()
        supportFragmentManager.beginTransaction()
            .add(binding.container.id, recFragment, "rec")
            .add(binding.container.id, resultFragment, "result")
            .hide(resultFragment)
            .commitAllowingStateLoss()
    }

    override fun getLayoutId(): Int = R.layout.activity_search
}