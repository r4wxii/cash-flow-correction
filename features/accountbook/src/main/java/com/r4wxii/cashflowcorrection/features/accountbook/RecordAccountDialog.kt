package com.r4wxii.cashflowcorrection.features.accountbook

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.r4wxii.cashflowcorrection.features.accountbook.databinding.DialogRecordAccountBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class RecordAccountDialog : DialogFragment() {
    private val viewModel: AccountBookViewModel by navGraphViewModels(R.id.account_book_navigation)
    private val datePickerBuilder = MaterialDatePicker.Builder.datePicker()

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_record_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DialogRecordAccountBinding.bind(view)
        binding.viewModel = viewModel

        binding.toolBar.setupWithNavController(findNavController())
        binding.toolBar.inflateMenu(R.menu.dialog_menu_item)
        binding.toolBar.setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_done) {
                println("done")
            }
            true
        }

        viewModel.recordEnabled.observe(viewLifecycleOwner) {
            binding.toolBar.menu.findItem(R.id.menu_done).isEnabled = it
        }

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