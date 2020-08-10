package com.r4wxii.cashflowcorrection.di

import com.r4wxii.cashflowcorrection.data.db.AccountDatabase
import com.r4wxii.cashflowcorrection.data.repository.di.DaggerRepositoryComponent
import com.r4wxii.cashflowcorrection.data.repository.di.RepositoryComponent
import com.r4wxii.cashflowcorrection.domain.model.repository.AccountRepository
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import com.r4wxii.cashflowcorrection.domain.usecase.di.DaggerUseCaseComponent
import com.r4wxii.cashflowcorrection.domain.usecase.di.UseCaseComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseComponentModule {
    @Provides
    fun provideAccountUseCase(useCaseComponent: UseCaseComponent): AccountUseCase = useCaseComponent.accountUseCase()

    @Singleton
    @Provides
    fun provideUseCaseComponent(accountRepository: AccountRepository): UseCaseComponent = DaggerUseCaseComponent.factory()
        .create(accountRepository)
}