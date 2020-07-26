package com.r4wxii.cashflowcorrection.domain.repository

import com.r4wxii.cashflowcorrection.domain.model.Account

interface AccountRepository {
    suspend fun insert(account: Account)
}