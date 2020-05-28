package com.r4wxii.cashflowcorrection.di

import com.r4wxii.cashflowcorrection.MainActivity
import com.r4wxii.cashflowcorrection.features.accountbook.AccountBookFragment
import com.r4wxii.cashflowcorrection.features.accountbook.di.AccountBookModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [AccountBookModule::class])
    abstract fun contributeAccountBookFragment(): AccountBookFragment
}