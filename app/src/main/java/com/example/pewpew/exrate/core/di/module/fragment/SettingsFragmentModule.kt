package com.example.pewpew.exrate.core.di.module.fragment

import androidx.recyclerview.widget.ItemTouchHelper
import com.example.pewpew.exrate.core.di.scope.FragmentScope
import com.example.pewpew.exrate.features.exchangerates.CustomItemTouchHelper
import dagger.Module
import dagger.Provides

@Module
class SettingsFragmentModule {
    @FragmentScope
    @Provides
    fun providesItemTouchHelper(customItemTouchHelper: CustomItemTouchHelper) =
            ItemTouchHelper(customItemTouchHelper)
}