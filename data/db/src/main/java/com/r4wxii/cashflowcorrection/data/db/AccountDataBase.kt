package com.r4wxii.cashflowcorrection.data.db

import kotlinx.coroutines.flow.Flow

interface AccountDatabase {
    suspend fun insertAccount(account: AccountEntity)
    suspend fun getThisMonthAccount(): Flow<List<AccountEntity>>
}