package com.r4wxii.cashflowcorrection.domain.model

import androidx.recyclerview.widget.DiffUtil
import java.time.LocalDate

data class Account(
    val id: Int,
    val quantity: Int,
    val date: LocalDate,
    val category: String,
    val subCategory: String?,
    val comment: String?
) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Account>() {
            override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean = oldItem == newItem
        }
    }
}