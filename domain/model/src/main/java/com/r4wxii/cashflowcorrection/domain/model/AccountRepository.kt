package com.r4wxii.cashflowcorrection.domain.model

import com.r4wxii.cashflowcorrection.domain.model.Account

interface AccountRepository {
    suspend fun insert(account: Account)
}