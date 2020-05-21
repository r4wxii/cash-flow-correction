package com.r4wxii.cashflowcorrection.data.repository

import com.r4wxii.cashflowcorrection.data.db.AccountDatabase
import com.r4wxii.cashflowcorrection.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val database: AccountDatabase
) : AccountRepository {

}