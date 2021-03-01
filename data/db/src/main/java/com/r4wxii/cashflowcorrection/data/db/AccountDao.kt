package com.r4wxii.cashflowcorrection.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AccountEntity)

    @Query("SELECT * FROM account WHERE id = :id")
    suspend fun getAccount(id: Int): AccountEntity

    @Query("SELECT * FROM account WHERE date(date)>=date(CURRENT_DATE, 'start of month')")
    fun getThisMonthAccounts(): Flow<List<AccountEntity>>

    @Delete
    fun delete(account: AccountEntity)
}