package com.r4wxii.cashflowcorrection.domain.model

import androidx.recyclerview.widget.DiffUtil
import java.time.LocalDate

data class Account private constructor(
    val id: Int,
    val quantity: Int,
    val date: LocalDate,
    val category: String,
    val subCategory: String?,
    val comment: String?
) {
    companion object {
        fun valueOf(
            id: Int,
            quantity: Int,
            date: LocalDate,
            category: String,
            subCategory: String?,
            comment: String?
        ): Account {
            return Account(
                id,
                quantity,
                date,
                category,
                subCategory,
                comment
            )
        }

        fun createNewItem(
            quantity: Int,
            date: LocalDate,
            category: String,
            subCategory: String?,
            comment: String?
        ): Account {
            return Account(
                0,
                quantity,
                date,
                category,
                subCategory,
                comment
            )
        }

        val diffCallback = object : DiffUtil.ItemCallback<Account>() {
            override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean =
                oldItem == newItem
        }
    }
}