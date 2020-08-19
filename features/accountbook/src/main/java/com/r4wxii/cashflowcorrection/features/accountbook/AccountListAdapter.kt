package com.r4wxii.cashflowcorrection.features.accountbook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.features.accountbook.databinding.CellAccountBinding

class AccountListAdapter : ListAdapter<Account, AccountViewHolder>(Account.diffCallback) {
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
        holder.binding.account = getItem(position)
    }
}

class AccountViewHolder(val binding: CellAccountBinding) : RecyclerView.ViewHolder(binding.root)