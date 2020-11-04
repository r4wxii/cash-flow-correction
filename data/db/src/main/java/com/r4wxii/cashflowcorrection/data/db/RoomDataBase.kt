package com.r4wxii.cashflowcorrection.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Database(entities = [AccountEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}

class RoomDatabaseImpl @Inject constructor(
    private val db: com.r4wxii.cashflowcorrection.data.db.Database,
    private val accountDao: AccountDao
) : AccountDatabase {
    override suspend fun insertAccount(account: AccountEntity) = accountDao.insert(account)

    override suspend fun getAccount(id: Int): AccountEntity = accountDao.getAccount(id)

    override suspend fun getThisMonthAccount(): Flow<List<AccountEntity>> = accountDao.getThisMonthAccounts()
}