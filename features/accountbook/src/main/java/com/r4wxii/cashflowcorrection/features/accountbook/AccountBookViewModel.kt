package com.r4wxii.cashflowcorrection.features.accountbook

import androidx.lifecycle.*
import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountBookViewModel @Inject constructor(
    private val useCase: AccountUseCase
) : ViewModel() {
    val accountData: LiveData<List<Account>> = useCase.data.asLiveData()

    init {
        viewModelScope.launch {
            useCase.getThisMonthAccounts()
        }
    }
}
