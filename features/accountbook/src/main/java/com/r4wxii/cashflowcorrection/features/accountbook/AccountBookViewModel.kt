package com.r4wxii.cashflowcorrection.features.accountbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.domain.repository.AccountRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

abstract class AccountBookViewModel : ViewModel() {
    abstract fun onClickFab()
}

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
    override fun onClickFab() {
        viewModelScope.launch {
            repository.insert(
                Account(
                    id = 0,
                    quantity = 0,
                    date = LocalDate.now(),
                    category = "",
                    subCategory = null,
                    comment = null
                )
            )
        }
    }
}
