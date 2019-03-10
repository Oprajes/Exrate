package com.example.pewpew.exrate.core.di.component

import com.example.pewpew.exrate.AndroidApplication
import com.example.pewpew.exrate.core.di.module.application.*
import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ActivityBinderModule::class,
    NetworkModule::class,
    DatabaseModule::class,
    RepositoryModule::class,
    ViewModelModule::class])
interface ApplicationComponent: AndroidInjector<AndroidApplication> {
    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<AndroidApplication>()
}