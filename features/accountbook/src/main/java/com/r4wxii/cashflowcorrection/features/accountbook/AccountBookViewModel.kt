package com.r4wxii.cashflowcorrection.features.accountbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

abstract class AccountBookViewModel : ViewModel() {
    abstract fun onClickFab()
}

class AccountBookViewModelFactory @Inject constructor(
    private val useCase: AccountUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountBookViewModelImpl(useCase) as T
    }
}

class AccountBookViewModelImpl @Inject constructor(
    private val useCase: AccountUseCase
) : AccountBookViewModel() {
    override fun onClickFab() {
        viewModelScope.launch {
            useCase.insert(
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
