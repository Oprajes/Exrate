package com.example.pewpew.exrate.core.di.module

import com.example.pewpew.exrate.core.di.scope.FragmentScope
import com.example.pewpew.exrate.features.exchangerates.view.ListFragment
import com.example.pewpew.exrate.features.exchangerates.view.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBinderModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindListFragment(): ListFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SettingsFragmentModule::class])
    abstract fun bindSettingsFragment(): SettingsFragment
}