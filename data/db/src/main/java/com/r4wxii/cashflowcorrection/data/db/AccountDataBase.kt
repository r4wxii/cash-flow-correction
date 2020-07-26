package com.r4wxii.cashflowcorrection.data.db

interface AccountDatabase {
    suspend fun insertAccount(account: AccountEntity)
}