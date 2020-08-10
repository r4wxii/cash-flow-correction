package com.r4wxii.cashflowcorrection.domain.model.repository

import com.r4wxii.cashflowcorrection.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun insert(account: Account)
    suspend fun getThisMonthAccounts(): Flow<List<Account>>
}