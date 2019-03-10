package com.example.pewpew.exrate.core.di.module

import com.example.pewpew.exrate.core.MainActivity
import com.example.pewpew.exrate.core.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinderModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBinderModule::class])
    abstract fun bindMainActivity(): MainActivity
}