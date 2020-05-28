package com.r4wxii.cashflowcorrection.features.accountbook.di

import com.r4wxii.cashflowcorrection.domain.repository.AccountRepository
import com.r4wxii.cashflowcorrection.features.accountbook.AccountBookViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AccountBookModule {
    @Provides
    fun provideViewModelFactory(repository: AccountRepository): AccountBookViewModelFactory = AccountBookViewModelFactory(repository)
}