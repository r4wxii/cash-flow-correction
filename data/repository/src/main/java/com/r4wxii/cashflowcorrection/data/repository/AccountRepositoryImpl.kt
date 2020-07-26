package com.r4wxii.cashflowcorrection.data.repository

import com.r4wxii.cashflowcorrection.data.db.AccountDatabase
import com.r4wxii.cashflowcorrection.data.db.AccountEntity
import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val database: AccountDatabase
) : AccountRepository {
    override suspend fun insert(account: Account) {
        database.insertAccount(account.toEntity())
    }
}

private fun AccountEntity.toAccount(): Account = Account(id, quantity, date, category, subCategory, comment)
private fun Account.toEntity(): AccountEntity = AccountEntity(id, quantity, date, category, subCategory, comment)