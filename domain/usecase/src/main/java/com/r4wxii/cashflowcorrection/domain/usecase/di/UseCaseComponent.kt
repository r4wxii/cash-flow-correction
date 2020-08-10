package com.r4wxii.cashflowcorrection.domain.usecase.di

import com.r4wxii.cashflowcorrection.domain.model.repository.AccountRepository
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import dagger.BindsInstance
import dagger.Component

@Component(modules = [UseCaseModule::class])
interface UseCaseComponent {
    fun accountUseCase(): AccountUseCase

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance accountRepository: AccountRepository
        ): UseCaseComponent
    }
}
