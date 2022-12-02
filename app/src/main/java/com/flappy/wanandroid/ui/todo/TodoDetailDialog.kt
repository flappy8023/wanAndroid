package com.flappy.wanandroid.ui.todo

import android.os.Build
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.flappy.wanandroid.R
import com.flappy.wanandroid.base.BaseBottomSheetDialog
import com.flappy.wanandroid.databinding.TodoDialogDetailBinding
import com.flappy.wanandroid.util.login.DateUtil
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

    override fun getLayoutId() = R.layout.todo_dialog_detail
    override fun initView() {

        todo?.let {
            binding.tvDate.text = it.dateStr
            binding.inputEtTitle.setText(it.title)
            binding.inputContent.setText(it.content)
        } ?: kotlin.run {
            val today = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.now().toString()
            } else {
                DateUtil.parseToString(Calendar.getInstance().timeInMillis)
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
            if (null == todo)
                viewModel.addTodo(title, content, date, type)
            else dismiss()
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
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.todo_add_date_picker_title))
            .build()
        datePicker.addOnPositiveButtonClickListener {
            binding.tvDate.text = DateUtil.parseToString(it)
        }
        datePicker.show(childFragmentManager, "datePicker")
    }


}