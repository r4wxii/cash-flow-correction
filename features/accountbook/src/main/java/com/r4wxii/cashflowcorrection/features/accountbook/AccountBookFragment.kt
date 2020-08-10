package com.r4wxii.cashflowcorrection.features.accountbook

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.r4wxii.cashflowcorrection.features.accountbook.databinding.FragmentAccountBookBinding
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AccountBookFragment(layoutId: Int = R.layout.fragment_account_book) : DaggerFragment(layoutId), HasAndroidInjector {
    @Inject
    lateinit var viewModelFactory: AccountBookViewModelFactory
    private val viewModel: AccountBookViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAccountBookBinding.bind(view)
        binding.viewModel = viewModel

        viewModel.accountData.observe(viewLifecycleOwner, Observer {
            Log.d("account data", it.toString())
        })
    }
}