package com.r4wxii.cashflowcorrection.data.repository.di

import com.r4wxii.cashflowcorrection.data.repository.AccountRepositoryImpl
import com.r4wxii.cashflowcorrection.domain.model.repository.AccountRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun provideAccountRepository(impl: AccountRepositoryImpl): AccountRepository
}