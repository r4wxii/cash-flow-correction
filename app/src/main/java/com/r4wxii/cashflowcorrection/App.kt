package com.r4wxii.cashflowcorrection

import com.r4wxii.cashflowcorrection.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : DaggerApplication(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.factory().create(this)
}