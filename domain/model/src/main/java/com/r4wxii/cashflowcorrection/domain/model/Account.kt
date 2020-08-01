package com.r4wxii.cashflowcorrection.domain.model

import java.time.LocalDate

data class Account(
    val id: Int,
    val quantity: Int,
    val date: LocalDate,
    val category: String,
    val subCategory: String?,
    val comment: String?
)