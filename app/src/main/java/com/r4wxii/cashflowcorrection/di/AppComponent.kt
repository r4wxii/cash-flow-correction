package com.r4wxii.cashflowcorrection.di

import com.r4wxii.cashflowcorrection.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [AndroidInjectionModule::class, ActivityModule::class])
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: MainApplication): AppComponent
    }

    override fun inject(app: MainApplication)
}