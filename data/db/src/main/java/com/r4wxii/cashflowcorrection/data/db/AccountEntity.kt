package com.r4wxii.cashflowcorrection.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity(tableName = "account")
@TypeConverters(DateConverter::class)
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val quantity: Int,
    val date: LocalDate,
    val category: String,
    val subCategory: String?,
    val comment: String
)