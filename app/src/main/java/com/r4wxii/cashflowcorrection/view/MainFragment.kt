package com.r4wxii.cashflowcorrection.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.r4wxii.cashflowcorrection.R
import com.r4wxii.cashflowcorrection.viewmodel.MainViewModel

class MainFragment(private val layoutId: Int = R.layout.fragment_main) : Fragment(layoutId) {
    private val viewModel: MainViewModel by viewModels()
}
