package com.r4wxii.cashflowcorrection.di

import android.app.Application
import com.r4wxii.cashflowcorrection.data.db.AccountDatabase
import com.r4wxii.cashflowcorrection.data.db.di.DaggerDbComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbComponentModule {
    @Singleton
    @Provides
    fun provideAccountDatabase(app: Application): AccountDatabase = DaggerDbComponent.factory()
        .create(app).accountDataBase()
}