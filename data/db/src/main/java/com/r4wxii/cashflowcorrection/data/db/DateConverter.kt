package com.r4wxii.cashflowcorrection.data.db

import androidx.room.TypeConverter
import java.time.LocalDate

internal class DateConverter {
    @TypeConverter
    fun fromString(value: String) = LocalDate.parse(value)

    @TypeConverter
    fun localDateToString(date: LocalDate): String = date.toString()

}