package com.r4wxii.cashflowcorrection.data.repository.di

import com.r4wxii.cashflowcorrection.data.db.AccountDatabase
import com.r4wxii.cashflowcorrection.domain.model.repository.AccountRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {
    fun accountRepository(): AccountRepository

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance accountDatabase: AccountDatabase
        ): RepositoryComponent
    }
}