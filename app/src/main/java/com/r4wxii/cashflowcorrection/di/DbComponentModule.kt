package com.r4wxii.cashflowcorrection.di

import android.app.Application
import com.r4wxii.cashflowcorrection.data.db.di.DaggerDbComponent
import com.r4wxii.cashflowcorrection.data.db.di.DbComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbComponentModule {
    @Singleton
    @Provides
    fun provideDbComponent(app: Application): DbComponent = DaggerDbComponent.factory()
        .create(app)
}