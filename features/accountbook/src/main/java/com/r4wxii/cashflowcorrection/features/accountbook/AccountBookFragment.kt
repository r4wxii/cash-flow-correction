package com.r4wxii.cashflowcorrection.features.accountbook

import androidx.fragment.app.viewModels
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AccountBookFragment(layoutId: Int = R.layout.fragment_account_book) : DaggerFragment(layoutId), HasAndroidInjector {
    @Inject
    lateinit var viewModelFactory: AccountBookViewModelFactory
    private val viewModel: AccountBookViewModel by viewModels { viewModelFactory }
}