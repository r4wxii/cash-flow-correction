package com.r4wxii.cashflowcorrection.features.accountbook.di

import com.r4wxii.cashflowcorrection.domain.model.repository.AccountRepository
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import com.r4wxii.cashflowcorrection.features.accountbook.AccountBookViewModelFactory
import com.r4wxii.cashflowcorrection.features.accountbook.RecordAccountViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AccountBookModule {
    @Provides
    fun provideAccountBookViewModelFactory(useCase: AccountUseCase): AccountBookViewModelFactory = AccountBookViewModelFactory(useCase)

    @Provides
    fun provideRecordAccountViewModelFactory(useCase: AccountUseCase): RecordAccountViewModelFactory = RecordAccountViewModelFactory(useCase)
}