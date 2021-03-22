package com.r4wxii.cashflowcorrection.features.accountbook

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.r4wxii.cashflowcorrection.features.accountbook.databinding.DialogRecordAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.*
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class RecordAccountDialog : DialogFragment(R.layout.dialog_record_account) {
    private val viewModel: RecordAccountViewModel by viewModels()
    private val datePickerBuilder = MaterialDatePicker.Builder.datePicker()
    private val navArgs: RecordAccountDialogArgs by navArgs()

    override fun onStart() {
        super.onStart()
        dialog?.let { dialog ->
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DialogRecordAccountBinding.bind(view)
        binding.viewModel = viewModel

        viewModel.getAccount(navArgs.id)

        binding.toolBar.setupWithNavController(findNavController())
        binding.toolBar.setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_done) {
                viewModel.saveAccount()
                findNavController().popBackStack()
            }
            true
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RecordAccountState.Init -> binding.toolBar.menu.findItem(R.id.menu_done).isEnabled = false
                is RecordAccountState.Fetched -> {
                    binding.dateForm.setText(state.account.date.toString())
                    binding.quantityForm.setText(state.account.quantity.toString())
                    binding.categoryForm.setText(state.account.category)
                    binding.subCategoryForm.setText(state.account.subCategory)
                    binding.commentForm.setText(state.account.comment)
                }
                is RecordAccountState.RecordableData -> binding.toolBar.menu.findItem(R.id.menu_done).isEnabled = true
            }
        }

        datePickerBuilder.setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        val datePicker = datePickerBuilder.build()
        datePicker.addOnPositiveButtonClickListener {
            binding.dateForm.setText(
                LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault()).format(
                    DateTimeFormatter.ISO_LOCAL_DATE
                )
            )
        }

        binding.dateForm.inputType = InputType.TYPE_NULL
        binding.dateForm.setOnClickListener {
            datePicker.show(childFragmentManager, "datePicker")
        }
        binding.dateForm.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                datePicker.show(childFragmentManager, "datePicker")
            }
        }
        binding.dateForm.addTextChangedListener(afterTextChanged = {
            viewModel.handleEvent(RecordAccountEvent.InputDate(it.toString()))
        })
        binding.quantityForm.inputType = InputType.TYPE_CLASS_NUMBER
        binding.quantityForm.addTextChangedListener(afterTextChanged = {
            viewModel.handleEvent(RecordAccountEvent.InputQuantity(it.toString()))
        })
        binding.categoryForm.addTextChangedListener(afterTextChanged = {
            viewModel.handleEvent(RecordAccountEvent.InputCategory(it.toString()))
        })
        binding.subCategoryForm.addTextChangedListener(afterTextChanged = {
            viewModel.handleEvent(RecordAccountEvent.InputSubCategory(it.toString()))
        })
        binding.commentForm.addTextChangedListener(afterTextChanged = {
            viewModel.handleEvent(RecordAccountEvent.InputComment(it.toString()))
        })
    }
}