package com.flappy.wanandroid.ui.search

import android.view.LayoutInflater
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseVMFragment
import com.flappy.wanandroid.databinding.SearchRecFragmentBinding
import com.google.android.material.chip.Chip

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:09 2022/9/21
 */
class SearchRecFragment : BaseVMFragment<SearchRecFragmentBinding, SearchVM>() {
    override fun bindViewModel() {
        viewModel.hotWords.observe(viewLifecycleOwner) {
            it?.let {
                setLabels(it)
            }
        }
    }

    override fun initView() {
    }

    override fun getLayoutId(): Int = R.layout.search_rec_fragment

    private fun setLabels(labels: List<String>) {
        labels.forEach { it ->
            val chip: Chip = LayoutInflater.from(requireContext())
                .inflate(R.layout.search_item_rec_chip, binding.labelLayoutHot, false) as Chip
            chip.text = it
            binding.labelLayoutHot.addView(chip)
        }
    }
}