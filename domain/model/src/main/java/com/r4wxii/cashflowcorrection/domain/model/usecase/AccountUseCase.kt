package com.r4wxii.cashflowcorrection.domain.model.usecase

import com.r4wxii.cashflowcorrection.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountUseCase {
    val data: Flow<List<Account>>

    suspend fun insert(account: Account)
    suspend fun getThisMonthAccounts()
}