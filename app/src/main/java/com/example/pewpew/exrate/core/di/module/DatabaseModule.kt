package com.example.pewpew.exrate.core.di.module

import android.content.Context
import androidx.room.Room
import com.example.pewpew.exrate.core.db.CurrencyDatabase
import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @ApplicationScope
    @Provides
    fun providesCurrencyDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            CurrencyDatabase::class.java,
            CurrencyDatabase.DB_NAME
        ).build()

    @ApplicationScope
    @Provides
    fun providesCurrencyDao(currencyDatabase: CurrencyDatabase) = currencyDatabase.getCurrencyDao()
}