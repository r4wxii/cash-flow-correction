package com.r4wxii.cashflowcorrection.features.accountbook

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.r4wxii.cashflowcorrection.features.accountbook.databinding.DialogRecordAccountBinding

class RecordAccountDialog : Fragment(R.layout.dialog_record_account) {
    private val viewModel: AccountBookViewModel by navGraphViewModels(R.id.account_book_navigation)
    private val datePickerBuilder = MaterialDatePicker.Builder.datePicker()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DialogRecordAccountBinding.bind(view)
        binding.viewModel = viewModel

        datePickerBuilder.setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        val datePicker = datePickerBuilder.build()
        datePicker.addOnPositiveButtonClickListener {
            binding.dateForm.setText(it.toString())
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
    }
}