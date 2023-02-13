package com.flappy.wanandroid.ui.todo

import android.os.Build
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseBottomSheetDialog
import com.flappy.wanandroid.databinding.TodoDialogDetailBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.LocalDate
import java.util.*

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:47 2022/11/28
 */
class TodoDetailDialog : BaseBottomSheetDialog<TodoDialogDetailBinding>() {
    private val args: TodoDetailDialogArgs by navArgs()
    private val type by lazy { args.type }
    private val todo by lazy { args.todo }
    private val viewModel: TodoVM by navGraphViewModels(R.id.todo)

    /**
     * 创建or编辑
     */
    private val isCreate: Boolean by lazy { todo == null }
    override fun getLayoutId() = R.layout.todo_dialog_detail
    override fun initView() {

        if (!isCreate) {
            binding.tvDate.text = todo!!.dateStr
            binding.inputEtTitle.setText(todo!!.title)
            binding.inputContent.setText(todo!!.content)
        } else {
            val today = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.now().toString()
            } else {
                com.flappy.util.DateUtil.parseToString(Calendar.getInstance().timeInMillis)
            }
            binding.tvDate.text = today
        }
        binding.tvDate.setOnClickListener {
            showDatePicker()
        }
        binding.ivClose.setOnClickListener {
            dismiss()
        }
        binding.btDone.setOnClickListener {
            val title = binding.inputEtTitle.text.toString()
            val content = binding.inputContent.text.toString()
            val date = binding.tvDate.text.toString()
            if (isCreate)
                viewModel.addTodo(title, content, date, type)
            else {
                todo!!.dateStr = date
                todo!!.title = title
                todo!!.content = content
                viewModel.updateTodo(todo!!)
            }
        }

    }

    override fun observeViewModel() {
        viewModel.addState.observe(viewLifecycleOwner) {
            when (it) {
                is TodoUIState.Loading -> {}
                is TodoUIState.Success -> {
                    dismiss()
                }
                is TodoUIState.Failure -> {}
            }
        }
        viewModel.editState.observe(viewLifecycleOwner) {
            when (it) {
                is TodoUIState.Loading -> {}
                is TodoUIState.Failure -> {}
                is TodoUIState.Success -> {
                    dismiss()
                }
            }
        }
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.todo_add_date_picker_title))
            .build()
        datePicker.addOnPositiveButtonClickListener {
            binding.tvDate.text = com.flappy.util.DateUtil.parseToString(it)
        }
        datePicker.show(childFragmentManager, "datePicker")
    }


}