package com.r4wxii.cashflowcorrection.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: AccountEntity)

    @Delete
    fun delete(account: AccountEntity)
}