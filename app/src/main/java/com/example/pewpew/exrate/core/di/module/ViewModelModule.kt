package com.example.pewpew.exrate.core.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import com.example.pewpew.exrate.core.di.viewmodel.ViewModelFactory
import com.example.pewpew.exrate.core.di.viewmodel.ViewModelKey
import com.example.pewpew.exrate.features.exchangerates.viewmodel.CurrencyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @ApplicationScope
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @ApplicationScope
    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    abstract fun bindCurrencyViewModel(currencyViewModel: CurrencyViewModel): ViewModel
}