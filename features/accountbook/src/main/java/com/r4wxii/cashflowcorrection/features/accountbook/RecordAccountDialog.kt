package com.r4wxii.cashflowcorrection.features.accountbook

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        viewModel.recordEnabled.observe(viewLifecycleOwner) {
            binding.toolBar.menu.findItem(R.id.menu_done).isEnabled = it
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
    }
}