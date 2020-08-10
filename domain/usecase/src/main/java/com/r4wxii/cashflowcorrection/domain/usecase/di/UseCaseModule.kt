package com.r4wxii.cashflowcorrection.domain.usecase.di

import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import com.r4wxii.cashflowcorrection.domain.usecase.AccountUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {
    @Binds
    fun provideAccountUseCase(impl: AccountUseCaseImpl): AccountUseCase
}
