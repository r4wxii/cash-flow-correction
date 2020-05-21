package com.r4wxii.cashflowcorrection.di

import com.r4wxii.cashflowcorrection.data.db.AccountDatabase
import com.r4wxii.cashflowcorrection.data.repository.di.DaggerRepositoryComponent
import com.r4wxii.cashflowcorrection.data.repository.di.RepositoryComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryComponentModule {
    @Singleton
    @Provides
    fun provideAccountRepository(accountDataBase: AccountDatabase): RepositoryComponent = DaggerRepositoryComponent.factory()
        .create(accountDataBase)
}