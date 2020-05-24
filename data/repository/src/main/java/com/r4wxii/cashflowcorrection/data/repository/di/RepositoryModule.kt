package com.r4wxii.cashflowcorrection.data.repository.di

import com.r4wxii.cashflowcorrection.data.repository.AccountRepositoryImpl
import com.r4wxii.cashflowcorrection.domain.repository.AccountRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class RepositoryModule {
    @Binds
    abstract fun provideAccountRepository(impl: AccountRepositoryImpl): AccountRepository
}