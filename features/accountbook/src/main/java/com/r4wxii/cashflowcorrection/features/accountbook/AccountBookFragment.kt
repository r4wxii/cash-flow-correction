package com.r4wxii.cashflowcorrection.features.accountbook

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.r4wxii.cashflowcorrection.features.accountbook.databinding.FragmentAccountBookBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountBookFragment(layoutId: Int = R.layout.fragment_account_book) : Fragment(layoutId) {
    private val viewModel: AccountBookViewModel by viewModels()

    private val accountListAdapter = AccountListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAccountBookBinding.bind(view)
        binding.viewModel = viewModel
        binding.accountList.adapter = accountListAdapter

        val sheetBehavior = BottomSheetBehavior.from(binding.frontLayer)
        sheetBehavior.isHideable = false
        sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        binding.filterButton.setOnClickListener {
            when (sheetBehavior.state) {
                BottomSheetBehavior.STATE_EXPANDED -> {
                    sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    binding.fab.hide()
                }
                BottomSheetBehavior.STATE_COLLAPSED -> {
                    sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    binding.fab.show()
                }
            }
        }

        viewModel.accountData.observe(viewLifecycleOwner, Observer {
            accountListAdapter.submitList(it)
        })

        binding.fab.setOnClickListener {
            findNavController().navigate(AccountBookFragmentDirections.actAccountBookToRecordAccount())
        }
    }
}