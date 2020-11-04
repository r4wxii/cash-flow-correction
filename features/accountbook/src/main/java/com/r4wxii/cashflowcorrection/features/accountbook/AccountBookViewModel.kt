package com.r4wxii.cashflowcorrection.features.accountbook

import androidx.lifecycle.*
import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class AccountBookViewModel : ViewModel() {
    abstract val accountData: LiveData<List<Account>>
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
    override val accountData: LiveData<List<Account>> = useCase.data.asLiveData()

    init {
        viewModelScope.launch {
            useCase.getThisMonthAccounts()
        }
    }
}
