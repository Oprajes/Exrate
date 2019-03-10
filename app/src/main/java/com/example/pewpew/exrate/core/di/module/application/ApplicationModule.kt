package com.example.pewpew.exrate.core.di.module.application

import android.content.Context
import com.example.pewpew.exrate.AndroidApplication
import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import com.example.pewpew.exrate.features.exchangerates.repository.Network
import com.example.pewpew.exrate.features.exchangerates.repository.NetworkInterface
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {
    @ApplicationScope
    @Binds
    abstract fun bindApplictionContext(application: AndroidApplication): Context
}