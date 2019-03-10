package com.example.pewpew.exrate.core.di.module.application

import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import com.example.pewpew.exrate.features.exchangerates.repository.Database
import com.example.pewpew.exrate.features.exchangerates.repository.DatabaseInterface
import com.example.pewpew.exrate.features.exchangerates.repository.Network
import com.example.pewpew.exrate.features.exchangerates.repository.NetworkInterface
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @ApplicationScope
    @Binds
    abstract fun bindNetwork(network: Network): NetworkInterface

    @ApplicationScope
    @Binds
    abstract fun bindDatabase(database: Database): DatabaseInterface
}