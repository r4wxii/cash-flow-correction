package com.r4wxii.cashflowcorrection.features.accountbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.r4wxii.cashflowcorrection.domain.repository.AccountRepository
import javax.inject.Inject

abstract class AccountBookViewModel : ViewModel()

class AccountBookViewModelFactory @Inject constructor(
    private val repository: AccountRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountBookViewModelImpl(repository) as T
    }
}

class AccountBookViewModelImpl @Inject constructor(
    private val repository: AccountRepository
) : AccountBookViewModel() {
    // TODO: Implement the ViewModel
}
