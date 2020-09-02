package com.r4wxii.cashflowcorrection.features.accountbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import javax.inject.Inject

class RecordAccountDialog : DialogFragment() {
    private val viewModel: AccountBookViewModel by navGraphViewModels(R.id.account_book_navigation)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.onClickFab()
        return inflater.inflate(R.layout.dialog_record_account, container, false)
    }
}