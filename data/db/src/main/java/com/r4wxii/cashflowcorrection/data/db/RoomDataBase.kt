package com.r4wxii.cashflowcorrection.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject

@Database(entities = [AccountEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}

class RoomDatabaseImpl @Inject constructor(
    private val db: com.r4wxii.cashflowcorrection.data.db.Database,
    private val accountDao: AccountDao
) : AccountDatabase {

}