package com.r4wxii.cashflowcorrection.features.accountbook

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.r4wxii.cashflowcorrection.features.accountbook.databinding.DialogRecordAccountBinding
import com.r4wxii.cashflowcorrection.features.accountbook.databinding.FragmentAccountBookBinding

class RecordAccountDialog : Fragment(R.layout.dialog_record_account) {
    private val viewModel: AccountBookViewModel by navGraphViewModels(R.id.account_book_navigation)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DialogRecordAccountBinding.bind(view)
        binding.viewModel = viewModel
    }
}