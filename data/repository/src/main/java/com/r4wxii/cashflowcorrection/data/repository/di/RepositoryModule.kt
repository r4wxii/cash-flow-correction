package com.r4wxii.cashflowcorrection.data.repository.di

import com.r4wxii.cashflowcorrection.data.repository.AccountRepositoryImpl
import com.r4wxii.cashflowcorrection.domain.model.repository.AccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun provideAccountRepository(impl: AccountRepositoryImpl): AccountRepository
}