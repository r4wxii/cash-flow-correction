package com.r4wxii.cashflowcorrection.domain.usecase

import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.domain.model.repository.AccountRepository
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class AccountUseCaseImpl @Inject constructor(
    private val repository: AccountRepository
): AccountUseCase {
    override val data = MutableStateFlow<List<Account>>(emptyList())

    override suspend fun insert(account: Account) = repository.insert(account)

    override suspend fun getThisMonthAccounts() {
        repository.getThisMonthAccounts().collect { list ->
            data.value = list
        }
    }
}