package com.r4wxii.cashflowcorrection.features.accountbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.features.accountbook.databinding.CellAccountBinding

class AccountListAdapter(private val onClickListener: (Int) -> Unit) : ListAdapter<Account, AccountViewHolder>(Account.diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            CellAccountBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = getItem(position)
        holder.binding.account = account
        holder.binding.root.setOnClickListener { onClickListener(account.id) }
    }
}

class AccountViewHolder(val binding: CellAccountBinding) : RecyclerView.ViewHolder(binding.root)