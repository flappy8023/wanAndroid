package com.flappy.wanandroid.ui.search

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseFragment
import com.flappy.wanandroid.databinding.SearchRecFragmentBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:09 2022/9/21
 */
@AndroidEntryPoint
class SearchRecFragment : BaseFragment<SearchRecFragmentBinding>() {
    val viewModel by viewModels<SearchVM>()
    fun bindViewModel() {
        viewModel.hotWords.observe(viewLifecycleOwner) {
            it?.let {
                setLabels(it)
            }
        }
    }

    override fun initView() {
        bindViewModel()
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