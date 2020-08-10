package com.r4wxii.cashflowcorrection.domain.model.usecase

import com.r4wxii.cashflowcorrection.domain.model.Account

interface AccountUseCase {
    suspend fun insert(account: Account)
}